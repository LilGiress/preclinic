package com.medecineWebApp.Asset.Management.mapper;

import com.medecineWebApp.Asset.Management.dto.MaintenanceDTO;
import com.medecineWebApp.Asset.Management.models.Maintenance;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    MaintenanceDTO toMaintenanceDTO(Maintenance maintenance);
    @InheritInverseConfiguration
    Maintenance toMaintenance(MaintenanceDTO maintenanceDTO);
}
