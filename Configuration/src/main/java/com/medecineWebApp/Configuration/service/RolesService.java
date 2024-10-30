package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.enums.PermissionType;
import com.medecineWebApp.Configuration.enums.RoleType;
import com.medecineWebApp.Configuration.models.role.Roles;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RolesService {
    Roles createRole(RoleType roleName, Set<PermissionType> permissions);
    Roles updateRole(Long id,RoleType roleName);
    Optional<Roles> findByName(RoleType name);
    List<Roles> findAllRoles();
    void deleteRole(Long id);
    Roles assignPermissionsToRole(RoleType roleName, Set<PermissionType> newPermissions);
    Roles removePermissionsFromRole(RoleType roleName, Set<PermissionType> oldPermissions);
}
