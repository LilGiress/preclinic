package com.medecineWebApp.Asset.Management.service.impl;

import com.medecineWebApp.Asset.Management.dto.AssetsDTO;
import com.medecineWebApp.Asset.Management.dto.externe.RolesDTO;
import com.medecineWebApp.Asset.Management.dto.externe.UsersDTO;
import com.medecineWebApp.Asset.Management.enums.AssetStatus;
import com.medecineWebApp.Asset.Management.exception.AssetsNotFoundException;
import com.medecineWebApp.Asset.Management.feignClient.UserFeignClient;
import com.medecineWebApp.Asset.Management.kafka.MaintenanceNotificationEvent;
import com.medecineWebApp.Asset.Management.mapper.AssetsMapper;
import com.medecineWebApp.Asset.Management.models.Assets;
import com.medecineWebApp.Asset.Management.repository.AssetsRepository;
import com.medecineWebApp.Asset.Management.service.AssetService;
import com.medecineWebApp.Asset.Management.utilis.KafkaNotificationProducer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl implements AssetService {
    private final AssetsRepository assetsRepository;
    private  AssetsMapper assetsMapper;
    private final KafkaNotificationProducer kafkaNotificationProducer;
    private final UserFeignClient userFeignClient;


    public AssetServiceImpl(AssetsRepository assetsRepository, KafkaNotificationProducer kafkaNotificationProducer, UserFeignClient userFeignClient) {
        this.assetsRepository = assetsRepository;

        this.kafkaNotificationProducer = kafkaNotificationProducer;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public AssetsDTO assignToDoctor(Long assetId, Long doctorId) {
        Assets asset = assetsRepository.findById(assetId)
                .orElseThrow(() -> new AssetsNotFoundException("Asset non trouvé"));

                if(doctorId == null) {
                throw new RuntimeException("Docteur non trouvé");
                }

        asset.setAssignedDoctorId(doctorId);
        return assetsMapper.toAssetsDTO(assetsRepository.save(asset));

    }

    @Override
    public AssetsDTO updateAsset( Assets assets) {
        Assets assets1= assetsRepository.findById(assets.getId()).orElseThrow(()->new RuntimeException("Asset non trouvé"));
        assets1.setName(assets.getName());
        assets1.setDescription(assets.getDescription());
        assets1.setStatus(assets.getStatus());
        assets1.setConditionAsset(assets.getConditionAsset());
        assets1.setWarranty(assets.getWarranty());
        assets1.setCategory(assets.getCategory());
        assets1.setUsageCount(assets.getUsageCount());
        assets1.setSerialNumber(assets.getSerialNumber());
        assets1.setAcquisitionDate(assets.getAcquisitionDate());
        assets1.setLastMaintenanceDate(assets.getLastMaintenanceDate());
        assets1.setMaintenanceLogs(assets.getMaintenanceLogs());
        assets1.setPurchaseFrom(assets.getPurchaseFrom());
        assets1.setNextMaintenanceDate(assets.getNextMaintenanceDate());
        assets1.setModel(assets.getModel());
        assets1.setSupplier(assets.getSupplier());
        assets1.setPurchaseDate(assets.getPurchaseDate());
        assets1.setMaintenanceThreshold(assets.getMaintenanceThreshold());
        assets1.setAssignedDoctorId(assets.getAssignedDoctorId());
        assets1.setValue(assets.getValue());


        return assetsMapper.toAssetsDTO(assetsRepository.save(assets1));
    }

    @Override
    public AssetsDTO createAsset(Assets assets) {

        if(assets.getAssignedDoctorId() == null) {
            throw new RuntimeException("Docteur non trouvé");
        }
        assets.setAssignedDoctorId(assets.getAssignedDoctorId());
        return assetsMapper.toAssetsDTO(assetsRepository.save(assets));
    }

    @Override
    public Page<AssetsDTO> getAllAssets(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        Page<Assets> assetsList = assetsRepository.findAll(pageable);
        return assetsList.map(assetsMapper::toAssetsDTO);
    }

    @Override
    public void deleteAsset(Long id) {
        assetsRepository.deleteById(id);

    }

    @Override
    // Exécuter cette tâche tous les jours à 3h du matin
    @Scheduled(cron = "0 0 3 * * ?")
    public void checkAndUpdateMaintenanceStatus() {
        List<Assets> assets = assetsRepository.findAll();

        // 🟢 1. Récupérer tous les rôles disponibles depuis le microservice Configuration
        List<RolesDTO> allRoles = userFeignClient.getAllRoles();

        // 🔍 2. Filtrer uniquement les rôles "ADMIN" et "TECHNICIAN"
        List<String> targetRoles = allRoles.stream()
                .map(RolesDTO::getName)
                .filter(role -> role.contains("ADMIN") || role.contains("TECHNICIAN"))
                .collect(Collectors.toList());

        // 🔄 3. Vérifier s'il y a des rôles à utiliser avant d'appeler l'autre FeignClient
        if (targetRoles.isEmpty()) {
            System.out.println("❌ Aucun rôle valide trouvé !");
            return;
        }

        // 🟢 4. Récupérer les utilisateurs avec les rôles filtrés
        List<UsersDTO> adminsAndTechnicians = userFeignClient.getUsersByRoles(targetRoles);

        for (Assets asset : assets) {
            if (needsMaintenance(asset)) {
                asset.setStatus(AssetStatus.IN_MAINTENANCE);
                asset.setLastMaintenanceDate(LocalDate.now());
                assetsRepository.save(asset);

                // 🔥 5. Envoyer une alerte Kafka aux administrateurs et techniciens
                for (UsersDTO user : adminsAndTechnicians) {
                    MaintenanceNotificationEvent event = new MaintenanceNotificationEvent(
                            user.getId().toString(),
                            "⚠️ L'asset '" + asset.getName() + "' (" + asset.getCategory() + ") nécessite une maintenance !",
                            "MAINTENANCE_ALERT"
                    );
                    kafkaNotificationProducer.sendNotification(event);
                }
            }
        }
    }


    private boolean needsMaintenance(Assets asset) {
        // Vérifier la durée depuis la dernière maintenance
//        if (asset.getLastMaintenanceDate() != null) {
//            long daysSinceLastMaintenance =
//                    LocalDate.now().toEpochDay() - asset.getLastMaintenanceDate().toEpochDay();
//            if (daysSinceLastMaintenance >= asset.getMaintenanceThreshold()) {
//                return true;
//            }
//        }
//
//        // Vérifier si le seuil d’utilisation est atteint
//        return asset.getUsageCount() >= asset.getMaintenanceThreshold();

        long daysSinceLastMaintenance =
                asset.getLastMaintenanceDate() != null ?
                        LocalDate.now().toEpochDay() - asset.getLastMaintenanceDate().toEpochDay() : Long.MAX_VALUE;

        return daysSinceLastMaintenance >= asset.getMaintenanceThreshold() ||
                asset.getUsageCount() >= asset.getMaintenanceThreshold();
    }
}
