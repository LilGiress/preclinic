package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.AssetDTO;
import com.medecineWebApp.Configuration.enums.AssetCategory;
import com.medecineWebApp.Configuration.enums.AssetStatus;
import com.medecineWebApp.Configuration.models.setting.Asset;

import java.util.List;
import java.util.Optional;

public interface AssetService {
    Optional<Asset> getAssetById(Long id);
    List<Asset> getAllAssets();
    Asset saveAsset(AssetDTO assetDTO);
    Asset updateAsset(Long id,AssetDTO assetDTO);
    void deleteAsset(Long id);
    Asset updateAssetStatus(Long id, AssetStatus status);
    List<Asset> findAssetsByCategory(AssetCategory category);
}
