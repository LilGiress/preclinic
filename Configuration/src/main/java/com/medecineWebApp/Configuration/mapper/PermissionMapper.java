package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.PermissionDTO;
import com.medecineWebApp.Configuration.models.role.Permission;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    @Mapping(target = "role", ignore = true)
    PermissionDTO permissionToPermissionDTO(Permission permission);
    @InheritInverseConfiguration
    Permission permissionDTOToPermission(PermissionDTO permissionDTO);

}
