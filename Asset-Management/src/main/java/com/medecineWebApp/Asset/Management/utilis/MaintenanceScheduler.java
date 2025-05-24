package com.medecineWebApp.Asset.Management.utilis;

import com.medecineWebApp.Asset.Management.models.Assets;
import com.medecineWebApp.Asset.Management.repository.AssetsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceScheduler {
    private final AssetsRepository assetsRepository;

    public MaintenanceScheduler(AssetsRepository assetsRepository) {
        this.assetsRepository = assetsRepository;
    }
    @Scheduled(cron = "0 0 8 * * ?") // Exécute tous les jours à 08:00
    public void checkMaintenanceAlerts() {
        List<Assets> assetsToMaintain = assetsRepository.findByMaintenanceRequiredTrue();
        for (Assets asset : assetsToMaintain) {
            System.out.println("⚠️ Maintenance nécessaire pour l'asset : " + asset.getName());
        }
    }
}
