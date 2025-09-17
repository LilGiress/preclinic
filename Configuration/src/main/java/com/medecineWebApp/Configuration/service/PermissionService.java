package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.PermissionDTO;
import com.medecineWebApp.Configuration.models.ModulePermission;
import com.medecineWebApp.Configuration.models.role.ActionPermission;
import com.medecineWebApp.Configuration.models.role.Permission;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PermissionService {
PermissionDTO createPermission(Permission permission);
PermissionDTO updatePermission(String label, List<ActionPermission> actionDTOs);
List<PermissionDTO> listPermissions();
List<ActionPermission> getActionsByLabel(String label);
PermissionDTO getPermissionById(Long id);
}
