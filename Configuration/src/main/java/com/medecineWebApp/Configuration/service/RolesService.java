package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.RolesDTO;
import com.medecineWebApp.Configuration.models.role.Permission;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.payload.request.UpdateRoleRequest;

import java.util.List;
import java.util.Optional;

public interface RolesService {
    RolesDTO updateRole(Long id,UpdateRoleRequest request);
    Optional<RolesDTO> findByName(String role);
    List<RolesDTO> findAllRoles();
    void deleteRole(Long id);
    RolesDTO createRoleWithPermissions(Roles role, List<Permission> permissions);

}
