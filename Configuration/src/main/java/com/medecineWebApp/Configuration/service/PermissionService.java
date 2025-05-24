package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.PermissionDTO;
import com.medecineWebApp.Configuration.models.ModulePermission;
import com.medecineWebApp.Configuration.models.role.Permission;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PermissionService {
PermissionDTO createPermission(List<Permission> permission);
PermissionDTO updatePermission(Long id,Permission permission);
Page<PermissionDTO> listPermissions(int page, int  size);
void deletePermission(Long permissionId);
ModulePermission createModulePermission(String moduleName, List<Permission> permissions);
List<ModulePermission> listModulePermissions();
List<Permission> listPermissions(List<Long> permissions);

}
