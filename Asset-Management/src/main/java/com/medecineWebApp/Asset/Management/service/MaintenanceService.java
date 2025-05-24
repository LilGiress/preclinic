package com.medecineWebApp.Asset.Management.service;

import com.medecineWebApp.Asset.Management.dto.MaintenanceDTO;
import com.medecineWebApp.Asset.Management.models.Maintenance;

import java.util.List;

public interface MaintenanceService {
    List<MaintenanceDTO> getAllMaintenances();
    MaintenanceDTO getMaintenanceById(Long id);
    MaintenanceDTO createMaintenance(Maintenance maintenance);
    void deleteMaintenance(Long id);
}
