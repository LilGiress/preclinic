package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.AssetDTO;
import com.medecineWebApp.Configuration.models.setting.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    AssetMapper INSTANCE = Mappers.getMapper(AssetMapper.class);
    AssetDTO assetToAssetDTO(Asset asset);
    Asset assetDTOToAsset(AssetDTO assetDTO);
}
