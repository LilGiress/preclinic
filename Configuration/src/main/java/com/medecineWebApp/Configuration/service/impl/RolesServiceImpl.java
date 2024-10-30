package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.enums.PermissionType;
import com.medecineWebApp.Configuration.enums.RoleType;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.repository.role.RoleRepository;
import com.medecineWebApp.Configuration.service.RolesService;
import jakarta.transaction.Transactional;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    // Create a new role with permissions
    public Roles createRole(RoleType roleName, Set<PermissionType> permissions) {
        // Check if the role already exists
        Optional<Roles> existingRole = roleRepository.findByName(roleName);
        if (existingRole.isPresent()) {
            throw new IllegalArgumentException("Role already exists: " + roleName);
        }

        // Create a new Roles entity
        Roles role = new Roles();
        role.setName(roleName);

        // Assign permissions, if provided
        if (permissions != null && !permissions.isEmpty()) {
            role.setPermissions(new HashSet<>(permissions));
        } else {
            role.setPermissions(new HashSet<>()); // Empty permissions if none provided
        }

        // Save and return the new role
        return roleRepository.save(role);
    }

    @Override
    public Roles updateRole(Long id, RoleType role) {
        Optional<Roles> existingrole= roleRepository.findById(id);
        if (existingrole.isPresent()) {
            Roles updateroles = existingrole.get();
            updateroles.setName(RoleType.valueOf(role.name()));
            updateroles.setPermissions(role.getPermissions());
            return roleRepository.save(updateroles);

        }
        throw new ResourceNotFoundException("Role with id " + id + " not found");
    }

    @Override
    public Optional<Roles> findByName(RoleType name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Roles> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(Long id) {
    roleRepository.deleteById(id);
    }

    @Override
    public Roles assignPermissionsToRole(RoleType roleName, Set<PermissionType> newPermissions) {
        Optional<Roles> roleOpt = roleRepository.findByName(roleName);

        if (roleOpt.isPresent()) {
            Roles role = roleOpt.get();

            // Add new permissions to the existing ones
            role.getPermissions().addAll(newPermissions);

            // Save the updated role back to the database
            return roleRepository.save(role);
        } else {
            throw new IllegalArgumentException("Role not found: " + roleName);
        }
    }

    @Override
    public Roles removePermissionsFromRole(RoleType roleName, Set<PermissionType> oldPermissions) {
        Optional<Roles> roleOpt = roleRepository.findByName(roleName);

        if (roleOpt.isPresent()) {
            Roles role = roleOpt.get();

            // Remove the specified permissions
            role.getPermissions().removeAll(oldPermissions);

            // Save the updated role back to the database
            return roleRepository.save(role);
        } else {
            throw new IllegalArgumentException("Role not found: " + roleName);
        }
    }
}
