package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.RolesDTO;
import com.medecineWebApp.Configuration.exception.PermissionNotFoundException;
import com.medecineWebApp.Configuration.exception.RolesNotFoundException;
import com.medecineWebApp.Configuration.mapper.RolesMapper;
import com.medecineWebApp.Configuration.models.role.Permission;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.payload.request.PermissionRequest;
import com.medecineWebApp.Configuration.payload.request.RolesRequest;
import com.medecineWebApp.Configuration.payload.request.UpdatePermissionRequest;
import com.medecineWebApp.Configuration.payload.request.UpdateRoleRequest;
import com.medecineWebApp.Configuration.repository.permission.PermissionRepository;
import com.medecineWebApp.Configuration.repository.role.RoleRepository;
import com.medecineWebApp.Configuration.service.RolesService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class RolesServiceImpl implements RolesService {

    private final RoleRepository roleRepository;
    private final RolesMapper rolesMapper;
    private final EntityManager entityManager;
    private final PermissionRepository permissionRepository;

    public RolesServiceImpl(RoleRepository roleRepository, RolesMapper rolesMapper, EntityManager entityManager, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.rolesMapper = rolesMapper;
        this.entityManager = entityManager;
        this.permissionRepository = permissionRepository;
    }

    @Override
// Create a new role with permissions
    public RolesDTO createRole(RolesRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new RolesNotFoundException("Role name cannot be null or empty.");
        }
        // Check if the role already exists
        Optional<Roles> existingRole = roleRepository.findByName(request.getName());
        if (existingRole.isPresent()) {
            throw new RolesNotFoundException("Role already exists: " + request.getName());
        }

        // Create a new Roles entity
        Roles role = new Roles();
        role.setName(request.getName().toUpperCase());
        log.info("Creating new permission --------------------: " + request.getPermissions());

        // Assign permissions, if provided
        if (request.getPermissions() != null && !request.getPermissions().isEmpty()) {
            Set<Permission> attachedPermissions = new HashSet<>();

            for (PermissionRequest permissionDTO : request.getPermissions()) {
                Permission permission = new Permission();
                permission.setModule(permissionDTO.getModule());
                permission.setCanRead(permissionDTO.isCanRead());
                permission.setCanWrite(permissionDTO.isCanWrite());
                permission.setCanCreate(permissionDTO.isCanCreate());
                permission.setCanDelete(permissionDTO.isCanDelete());
                permission.setCanImport(permissionDTO.isCanImport());
                permission.setCanExport(permissionDTO.isCanExport());
                permission.setCanApprove(permissionDTO.isCanApprove());
                permission.setCanValidate(permissionDTO.isCanValidate());
                permission.setCanAssign(permissionDTO.isCanAssign());
                permission.setCanGenerateReport(permissionDTO.isCanGenerateReport());
                permission.setCanActivate(permissionDTO.isCanActivate());
               Roles rol = roleRepository.save(role);
                permission.setRole(rol);  // Associez chaque permission au rôle
                attachedPermissions.add(permission);
            }

            role.setPermissions(attachedPermissions);
        } else {
            role.setPermissions(new HashSet<>()); // Empty permissions if none provided
        }

        // Save and return the new role
        log.info("liste of permissions --------------------: " + request.getPermissions());
        return rolesMapper.rolesToRolesDTO(roleRepository.save(role));
    }


    @Override
    public RolesDTO updateRole(Long roleId, UpdateRoleRequest request) {
        // Vérifier si le rôle existe
        Roles role =  roleRepository.findById(roleId)
                .orElseThrow(() -> new RolesNotFoundException("Role with ID " + roleId + " does not exist."));
        role.setName(request.getName().toUpperCase());
        log.info("Updating role --------------------: " + request.getName());

        // Mettre à jour les permissions, si elles sont fournies
        if (request.getPermissions() != null && !request.getPermissions().isEmpty()) {
            Set<Permission> updatedPermissions = new HashSet<>();

            // Gérer la mise à jour des permissions
            // 3. Supprimer les permissions existantes (optionnel, selon votre logique)
            // Si vous voulez mettre à jour complètement les permissions, vous pouvez supprimer celles existantes
            for (UpdatePermissionRequest permissionDTO : request.getPermissions()) {
                Permission permission = permissionRepository.findById(permissionDTO.getId())
                        .orElseThrow(() -> new PermissionNotFoundException("Permission not found"));
                permission.setModule(permissionDTO.getModule());
                permission.setCanRead(permissionDTO.isCanRead());
                permission.setCanWrite(permissionDTO.isCanWrite());
                permission.setCanCreate(permissionDTO.isCanCreate());
                permission.setCanDelete(permissionDTO.isCanDelete());
                permission.setCanImport(permissionDTO.isCanImport());
                permission.setCanExport(permissionDTO.isCanExport());
                permission.setCanApprove(permissionDTO.isCanApprove());
                permission.setCanValidate(permissionDTO.isCanValidate());
                permission.setCanAssign(permissionDTO.isCanAssign());
                permission.setCanGenerateReport(permissionDTO.isCanGenerateReport());
                permission.setCanActivate(permissionDTO.isCanActivate());

                permission.setRole(role);  // Associez la permission au rôle
                updatedPermissions.add(permission);
            }

            role.setPermissions(updatedPermissions);
        } else {
            role.setPermissions(new HashSet<>()); // Aucune permission fournie, vide les permissions du rôle
        }

        // Sauvegarder et retourner le rôle mis à jour
        log.info("Updated list of permissions --------------------: " + request.getPermissions());
        return rolesMapper.rolesToRolesDTO(roleRepository.save(role));
    }


    @Override
    public Optional<RolesDTO> findByName(String role) {
        return roleRepository.findByName(role).map(rolesMapper::rolesToRolesDTO);
    }

    @Override
    public List<RolesDTO> findAllRoles() {

        return roleRepository.findAll().stream().map(rolesMapper::rolesToRolesDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteRole(Long roleId) {
        // Vérifier si le rôle existe
        Roles role =  roleRepository.findById(roleId)
                .orElseThrow(() -> new RolesNotFoundException("Role not found"));
        // 2. Supprimer les permissions associées (cascades de suppression)
        permissionRepository.deleteAll(role.getPermissions());
        // Supprimer le rôle
        roleRepository.delete(role);
        log.info("Deleted role --------------------: " + role.getName());
    }


}
