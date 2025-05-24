package com.medecineWebApp.Asset.Management.mapper;

import com.medecineWebApp.Asset.Management.dto.AssetMaintenanceLogDTO;
import com.medecineWebApp.Asset.Management.models.AssetMaintenanceLog;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AssetMaintenanceLogMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    AssetMaintenanceLogDTO assetMaintenanceLogToAssetMaintenanceLogDTO(AssetMaintenanceLog assetMaintenanceLog);
    @InheritInverseConfiguration
    AssetMaintenanceLog assetMaintenanceLogDTOToAssetMaintenanceLog(AssetMaintenanceLogDTO assetMaintenanceLogDTO);
}
