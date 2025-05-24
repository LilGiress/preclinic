package com.medecineWebApp.Asset.Management.service;

import com.medecineWebApp.Asset.Management.dto.AssetMaintenanceLogDTO;
import com.medecineWebApp.Asset.Management.models.AssetMaintenanceLog;

import java.util.List;

public interface AssetMaintenanceLogService {
    List<AssetMaintenanceLogDTO> getAllLogs();
    AssetMaintenanceLogDTO logMaintenance(AssetMaintenanceLog assetMaintenanceLog);
}
