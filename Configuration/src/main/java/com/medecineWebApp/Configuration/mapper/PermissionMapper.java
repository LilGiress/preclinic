package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.PermissionDTO;
import com.medecineWebApp.Configuration.models.role.Permission;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDTO permissionToPermissionDTO(Permission permission);
    @InheritInverseConfiguration
    Permission permissionDTOToPermission(PermissionDTO permissionDTO);

}
