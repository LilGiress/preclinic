package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.enums.PermissionType;
import com.medecineWebApp.Configuration.enums.RoleType;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.service.RolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }
    // Endpoint to create a new role with permissions
    @PostMapping("/create")
    public ResponseEntity<Roles> createRole(
            @RequestParam("name") RoleType roleName,
            @RequestBody Set<PermissionType> permissions) {
        Roles createdRole = rolesService.createRole(roleName, permissions);
        return ResponseEntity.ok(createdRole);
    }


    // Endpoint to update  role
    @PutMapping("/update")
    public ResponseEntity<Roles> updateRole(
            @RequestParam("id") Long id,
            @RequestBody RoleType roleName) {
        return ResponseEntity.ok(rolesService.updateRole(id,roleName));
    }


    // Assign permissions to a role
    @PostMapping("/{roleName}/assign-permissions")
    public ResponseEntity<Roles> assignPermissionsToRole(
            @PathVariable RoleType roleName,
            @RequestBody Set<PermissionType> permissions) {
        Roles updatedRole = rolesService.assignPermissionsToRole(roleName, permissions);
        return ResponseEntity.ok(updatedRole);
    }

    // Remove permissions from a role
    @PostMapping("/{roleName}/remove-permissions")
    public ResponseEntity<Roles> removePermissionsFromRole(
            @PathVariable RoleType roleName,
            @RequestBody Set<PermissionType> permissions) {
        Roles updatedRole = rolesService.removePermissionsFromRole(roleName, permissions);
        return ResponseEntity.ok(updatedRole);
    }

    // Get role by name
    @GetMapping("/{roleName}")
    public ResponseEntity<Optional<Roles>> getRoleByName(@PathVariable RoleType roleName) {
        return ResponseEntity.ok(rolesService.findByName(roleName));
    }

    // Get all roles
    @GetMapping
    public ResponseEntity<List<Roles>> getAllRoles() {
        List<Roles> roles = rolesService.findAllRoles();
        return ResponseEntity.ok(roles);
    }
    @DeleteMapping
    public void deleteRole(@RequestParam("id") Long id) {
          rolesService.deleteRole(id);
    }

}
