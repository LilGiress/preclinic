package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.ActionPermissionDTO;
import com.medecineWebApp.Configuration.models.role.ActionPermission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActionPermissionMapper {
    ActionPermissionDTO mapToDTO(ActionPermission actionPermission);
    ActionPermission  maptoActionPermission(ActionPermissionDTO actionPermissionDTO);
}
