package com.medecineWebApp.Asset.Management.controller;

import com.medecineWebApp.Asset.Management.dto.AssetsDTO;
import com.medecineWebApp.Asset.Management.enums.AssetStatus;
import com.medecineWebApp.Asset.Management.models.Assets;
import com.medecineWebApp.Asset.Management.repository.AssetsRepository;
import com.medecineWebApp.Asset.Management.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService assetService;
    private final AssetsRepository assetsRepository;

    public AssetController(AssetService assetService, AssetsRepository assetsRepository) {
        this.assetService = assetService;
        this.assetsRepository = assetsRepository;
    }

    // Assigner un équipement à un docteur
    @PutMapping("/{assetId}/assign/{doctorId}")
    public ResponseEntity<AssetsDTO> assignAssetToDoctor(@PathVariable Long assetId, @PathVariable Long doctorId) {
        return ResponseEntity.ok(assetService.assignToDoctor(assetId, doctorId));
    }
    @PutMapping("/{id}/maintenance")
    public ResponseEntity<String> markAssetForMaintenance(@PathVariable Long id) {
        Optional<Assets> assetOpt = assetsRepository.findById(id);

        if (assetOpt.isPresent()) {
            Assets asset = assetOpt.get();
            asset.setStatus(AssetStatus.IN_MAINTENANCE);
            asset.setLastMaintenanceDate(LocalDate.now());
            assetsRepository.save(asset);

            return ResponseEntity.ok("✅ Asset mis en maintenance !");
        } else {
            return ResponseEntity.badRequest().body("❌ Asset non trouvé !");
        }
    }
}
