package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.exception.enums.AssetCategory;
import com.medecineWebApp.Configuration.exception.enums.AssetStatus;
import com.medecineWebApp.Configuration.models.setting.Asset;

import java.util.List;
import java.util.Optional;

public interface AssetService {
    Optional<Asset> getAssetById(Long id);
    List<Asset> getAllAssets();
    Asset saveAsset(Asset asset);
    Asset updateAsset(Long id,Asset asset);
    void deleteAsset(Long id);
    Asset updateAssetStatus(Long id, AssetStatus status);
    List<Asset> findAssetsByCategory(AssetCategory category);
}
