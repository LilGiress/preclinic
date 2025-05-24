package com.medecineWebApp.Asset.Management.controller;

import com.medecineWebApp.Asset.Management.dto.AssetMaintenanceLogDTO;
import com.medecineWebApp.Asset.Management.dto.MaintenanceDTO;
import com.medecineWebApp.Asset.Management.models.Maintenance;
import com.medecineWebApp.Asset.Management.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }
    @GetMapping
    public ResponseEntity<List<MaintenanceDTO>> getAllMaintenances() {
        return ResponseEntity.ok(maintenanceService.getAllMaintenances()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> getMaintenanceById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceById(id));
    }

    @PostMapping
    public ResponseEntity<MaintenanceDTO> createMaintenance(@RequestBody Maintenance maintenance) {
        return ResponseEntity.ok(maintenanceService.createMaintenance(maintenance)) ;
    }

    @DeleteMapping("/{id}")
    public void deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
    }
}
