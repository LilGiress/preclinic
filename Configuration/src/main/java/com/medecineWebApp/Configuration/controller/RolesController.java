package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.RolesDTO;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.payload.request.RolesRequest;
import com.medecineWebApp.Configuration.payload.request.UpdateRoleRequest;
import com.medecineWebApp.Configuration.service.RolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @PostMapping
    public ResponseEntity<RolesDTO> createRole(@RequestBody RolesRequest request) {
        Roles role = new Roles();
        role.setName(request.getName());

        return ResponseEntity.ok(rolesService.createRoleWithPermissions(role, request.getPermissions()));
    }


    // Endpoint to update  role
  //  @PreAuthorize("hasPermission(authentication.principal.username, 'PATIENT', 'WRITE')")
    @PutMapping("/update")
    public ResponseEntity<RolesDTO> updateRole(
            @RequestParam("id") Long id,
            @RequestBody UpdateRoleRequest request) {
        return ResponseEntity.ok(rolesService.updateRole(id,request));
    }

    // Get role by name
    @GetMapping("/{roleName}")
    public ResponseEntity<Optional<RolesDTO>> getRoleByName(@PathVariable String roleName) {
        return ResponseEntity.ok(rolesService.findByName(roleName));
    }

    // Get all roles
    @GetMapping("/all")
    public ResponseEntity<List<RolesDTO>> getAllRoles(

    ) {
        return ResponseEntity.ok(rolesService.findAllRoles());
    }
    @DeleteMapping
    public void deleteRole(@RequestParam("id") Long id) {
          rolesService.deleteRole(id);
    }

}
