package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.RolesDTO;
import com.medecineWebApp.Configuration.enums.PermissionType;
import com.medecineWebApp.Configuration.enums.RoleType;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.service.RolesService;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<RolesDTO> createRole(
            @RequestParam("name") RoleType roleName,
            @RequestBody Set<PermissionType> permissions) {
        return ResponseEntity.ok(rolesService.createRole(roleName, permissions));
    }


    // Endpoint to update  role
    @PutMapping("/update")
    public ResponseEntity<RolesDTO> updateRole(
            @RequestParam("id") Long id,
            @RequestBody RoleType roleName) {
        return ResponseEntity.ok(rolesService.updateRole(id,roleName));
    }


    // Assign permissions to a role
    @PostMapping("/{roleName}/assign-permissions")
    public ResponseEntity<RolesDTO> assignPermissionsToRole(
            @PathVariable RoleType roleName,
            @RequestBody Set<PermissionType> permissions) {
        return ResponseEntity.ok(rolesService.assignPermissionsToRole(roleName, permissions));
    }

    // Remove permissions from a role
    @PostMapping("/{roleName}/remove-permissions")
    public ResponseEntity<RolesDTO> removePermissionsFromRole(
            @PathVariable RoleType roleName,
            @RequestBody Set<PermissionType> permissions) {
        return ResponseEntity.ok(rolesService.removePermissionsFromRole(roleName, permissions));
    }

    // Get role by name
    @GetMapping("/{roleName}")
    public ResponseEntity<Optional<RolesDTO>> getRoleByName(@PathVariable RoleType roleName) {
        return ResponseEntity.ok(rolesService.findByName(roleName));
    }

    // Get all roles
    @GetMapping
    public ResponseEntity<Page<RolesDTO>> getAllRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(rolesService.findAllRoles(page, size));
    }
    @DeleteMapping
    public void deleteRole(@RequestParam("id") Long id) {
          rolesService.deleteRole(id);
    }

}
