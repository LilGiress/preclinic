package com.medecineWebApp.Asset.Management.service.impl;

import com.medecineWebApp.Asset.Management.dto.AssetMaintenanceLogDTO;
import com.medecineWebApp.Asset.Management.mapper.AssetMaintenanceLogMapper;
import com.medecineWebApp.Asset.Management.models.AssetMaintenanceLog;
import com.medecineWebApp.Asset.Management.models.Assets;
import com.medecineWebApp.Asset.Management.models.Maintenance;
import com.medecineWebApp.Asset.Management.repository.AssetMaintenanceLogRepository;
import com.medecineWebApp.Asset.Management.repository.AssetsRepository;
import com.medecineWebApp.Asset.Management.repository.MaintenanceRepository;
import com.medecineWebApp.Asset.Management.service.AssetMaintenanceLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetMaintenanceLogServiceImpl implements AssetMaintenanceLogService {
    private  AssetMaintenanceLogMapper assetMaintenanceLogMapper;
    private final AssetMaintenanceLogRepository assetMaintenanceLogRepository;
    private final AssetsRepository assetsRepository;
    private final MaintenanceRepository maintenanceRepository;


    public AssetMaintenanceLogServiceImpl( AssetMaintenanceLogRepository assetMaintenanceLogRepository, AssetsRepository assetsRepository, MaintenanceRepository maintenanceRepository) {

        this.assetMaintenanceLogRepository = assetMaintenanceLogRepository;
        this.assetsRepository = assetsRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public List<AssetMaintenanceLogDTO> getAllLogs() {
        List<AssetMaintenanceLog> logs = assetMaintenanceLogRepository.findAll();
        return logs.stream()
                .map(assetMaintenanceLogMapper::assetMaintenanceLogToAssetMaintenanceLogDTO)
                .collect(Collectors.toList());
    }


    @Override
    public AssetMaintenanceLogDTO logMaintenance(AssetMaintenanceLog assetMaintenanceLog) {
        Assets assets= assetsRepository.findById(assetMaintenanceLog.getAsset().getId()).orElseThrow(()-> new RuntimeException("Asset Not Found"));
        Maintenance maintenance = maintenanceRepository.findById(assetMaintenanceLog.getMaintenance().getId()).orElseThrow(()-> new RuntimeException("Maintenance Not Found"));
        assetMaintenanceLog.setMaintenance(maintenance);
        assetMaintenanceLog.setAsset(assets);
        assetMaintenanceLog.setMaintenanceDate(LocalDate.now());
        return assetMaintenanceLogMapper.assetMaintenanceLogToAssetMaintenanceLogDTO(assetMaintenanceLog);
    }
}
