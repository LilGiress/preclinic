package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.RolesDTO;
import com.medecineWebApp.Configuration.enums.PermissionType;
import com.medecineWebApp.Configuration.enums.RoleType;
import com.medecineWebApp.Configuration.models.role.Roles;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RolesService {
    RolesDTO createRole(RoleType roleName, Set<PermissionType> permissions);
    RolesDTO updateRole(Long id,RoleType roleName);
    Optional<RolesDTO> findByName(RoleType name);
    Page<RolesDTO> findAllRoles(int page, int size);
    void deleteRole(Long id);
    RolesDTO assignPermissionsToRole(RoleType roleName, Set<PermissionType> newPermissions);
    RolesDTO removePermissionsFromRole(RoleType roleName, Set<PermissionType> oldPermissions);
}
