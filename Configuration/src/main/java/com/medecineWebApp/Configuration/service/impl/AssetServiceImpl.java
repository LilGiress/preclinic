package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.AssetDTO;
import com.medecineWebApp.Configuration.enums.AssetCategory;
import com.medecineWebApp.Configuration.enums.AssetStatus;
import com.medecineWebApp.Configuration.mapper.AssetMapper;
import com.medecineWebApp.Configuration.models.setting.Asset;
import com.medecineWebApp.Configuration.repository.AssetRepository;
import com.medecineWebApp.Configuration.service.AssetService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    public AssetServiceImpl(AssetRepository assetRepository, AssetMapper assetMapper) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
    }

    @Override
    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset saveAsset(AssetDTO assetDTO) {

        return assetRepository.save(assetMapper.assetDTOToAsset(assetDTO));
    }

    @Override
    public Asset updateAsset(Long id, AssetDTO asset) {
        Optional<Asset> assetOptional = assetRepository.findById(id);
        if (assetOptional.isPresent()) {
            Asset assetToUpdate = assetOptional.get();
            assetToUpdate.setName(asset.getName());
            assetToUpdate.setDescription(asset.getDescription());
            assetToUpdate.setWarranty(asset.getWarranty());
            assetToUpdate.setValue(asset.getValue());
            assetToUpdate.setUser(asset.getUser());
            assetToUpdate.setSupplier(asset.getSupplier());
            assetToUpdate.setCategory(asset.getCategory());
            assetToUpdate.setStatus(asset.getStatus());
            assetToUpdate.setSerialNumber(asset.getSerialNumber());
            assetToUpdate.setPurchaseFrom(asset.getPurchaseFrom());
            assetToUpdate.setPurchaseDate(asset.getPurchaseDate());
            assetToUpdate.setModel(asset.getModel());
            assetToUpdate.setCondition(asset.getCondition());
            assetToUpdate.setAcquisitionDate(asset.getAcquisitionDate());
            return assetRepository.save(assetToUpdate);

        }
       throw new ResourceNotFoundException("Asset with id " + id + " not found");
    }

    @Override
    public void deleteAsset(Long id) {
    assetRepository.deleteById(id);
    }

    @Override
    public Asset updateAssetStatus(Long id, AssetStatus status) {
        return assetRepository.findById(id).map(asset -> {
            asset.setStatus(status);
            return assetRepository.save(asset);
        }).orElseThrow(() -> new RuntimeException("Asset not found"));
    }

    @Override
    public List<Asset> findAssetsByCategory(AssetCategory category) {
        return assetRepository.findByCategory(category);
    }
}
