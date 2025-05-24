package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.RolesDTO;
import com.medecineWebApp.Configuration.payload.request.RolesRequest;
import com.medecineWebApp.Configuration.payload.request.UpdateRoleRequest;

import java.util.List;
import java.util.Optional;

public interface RolesService {
    RolesDTO createRole(RolesRequest request);
    RolesDTO updateRole(Long id,UpdateRoleRequest request);
    Optional<RolesDTO> findByName(String role);
    List<RolesDTO> findAllRoles();
    void deleteRole(Long id);

}
