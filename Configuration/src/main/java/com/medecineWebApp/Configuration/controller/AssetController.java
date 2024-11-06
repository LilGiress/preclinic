package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.AssetDTO;
import com.medecineWebApp.Configuration.enums.AssetCategory;
import com.medecineWebApp.Configuration.enums.AssetStatus;
import com.medecineWebApp.Configuration.models.setting.Asset;
import com.medecineWebApp.Configuration.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }
    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        return assetService.getAssetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody AssetDTO asset) {
        return ResponseEntity.ok( assetService.saveAsset(asset)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody AssetDTO asset) {
        try {
            Asset updatedAsset = assetService.updateAsset(id, asset);
            return ResponseEntity.ok(updatedAsset);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Asset> updateAssetStatus(@PathVariable Long id, @RequestParam AssetStatus status) {
        try {
            Asset updatedAsset = assetService.updateAssetStatus(id, status);
            return ResponseEntity.ok(updatedAsset);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category")
    public ResponseEntity<List<Asset>> updateAssetCategory( @RequestParam AssetCategory category) {
        return ResponseEntity.ok(assetService.findAssetsByCategory(category));
    }


}
