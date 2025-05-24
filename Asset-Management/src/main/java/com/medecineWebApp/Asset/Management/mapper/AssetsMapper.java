package com.medecineWebApp.Asset.Management.mapper;

import com.medecineWebApp.Asset.Management.dto.AssetsDTO;
import com.medecineWebApp.Asset.Management.models.Assets;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface AssetsMapper {
    @InheritInverseConfiguration
    Assets toAssets(AssetsDTO assetsDTO);
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    AssetsDTO toAssetsDTO(Assets assets);
}
