package com.medecineWebApp.Asset.Management.service;

import com.medecineWebApp.Asset.Management.dto.AssetsDTO;
import com.medecineWebApp.Asset.Management.models.Assets;
import org.springframework.data.domain.Page;



public interface AssetService {
    AssetsDTO assignToDoctor(Long assetId, Long doctorId);
    AssetsDTO updateAsset( Assets assets);
    AssetsDTO createAsset(Assets assets);
    Page<AssetsDTO> getAllAssets(int page, int size);
    void deleteAsset(Long id);
    void checkAndUpdateMaintenanceStatus();
//    Optional<AssetDTO> getAssetById(Long id);
//    List<AssetDTO> getAllAssets();
//    AssetDTO saveAsset(Asset asset);
//    AssetDTO updateAsset(Long id,Asset asset);
//    void deleteAsset(Long id);
//    AssetDTO updateAssetStatus(Long id, AssetStatus status);
//    List<AssetDTO> findAssetsByCategory(AssetCategory category);
}
