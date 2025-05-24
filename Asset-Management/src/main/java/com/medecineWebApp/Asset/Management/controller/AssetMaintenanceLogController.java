package com.medecineWebApp.Asset.Management.controller;

import com.medecineWebApp.Asset.Management.dto.AssetMaintenanceLogDTO;
import com.medecineWebApp.Asset.Management.models.AssetMaintenanceLog;
import com.medecineWebApp.Asset.Management.service.AssetMaintenanceLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-logs")
public class AssetMaintenanceLogController {
    private final AssetMaintenanceLogService assetMaintenanceLogService;

    public AssetMaintenanceLogController(AssetMaintenanceLogService assetMaintenanceLogService) {
        this.assetMaintenanceLogService = assetMaintenanceLogService;
    }
    @GetMapping
    public ResponseEntity <List<AssetMaintenanceLogDTO>> getAllLogs() {
        return ResponseEntity.ok(assetMaintenanceLogService.getAllLogs());
    }

    @PostMapping("/log")
    public ResponseEntity <AssetMaintenanceLogDTO> logMaintenance(
            @RequestBody AssetMaintenanceLog assetMaintenanceLog
            ) {
        return ResponseEntity.ok(assetMaintenanceLogService.logMaintenance(assetMaintenanceLog)) ;
    }
}
