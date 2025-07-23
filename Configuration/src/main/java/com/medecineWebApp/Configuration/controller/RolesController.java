package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.RolesDTO;
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
    // Endpoint to create a new role with permissions
    @PostMapping("/create")
    public ResponseEntity<RolesDTO> createRole(
            @RequestBody RolesRequest request) {
        return ResponseEntity.ok(rolesService.createRole(request));
    }


    // Endpoint to update  role
  //  @PreAuthorize("hasPermission(authentication.principal.username, 'PATIENT', 'WRITE')")
    @PutMapping("/update")
    public ResponseEntity<RolesDTO> updateRole(
            @RequestParam("id") Long id,
            @RequestBody UpdateRoleRequest roleName) {
        return ResponseEntity.ok(rolesService.updateRole(id,roleName));
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
