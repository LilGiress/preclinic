package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.PermissionDTO;
import com.medecineWebApp.Configuration.models.ModulePermission;
import com.medecineWebApp.Configuration.models.role.ActionPermission;
import com.medecineWebApp.Configuration.models.role.Permission;
import com.medecineWebApp.Configuration.service.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
    @PostMapping
    public ResponseEntity<PermissionDTO> createPermission(@RequestBody Permission permission) {
        return ResponseEntity.ok(permissionService.createPermission(permission));
    }

    @PutMapping("/update")
    public ResponseEntity<PermissionDTO> updatePermission(@RequestParam String label, @RequestBody List<ActionPermission> permission) {
        return ResponseEntity.ok(permissionService.updatePermission(label,permission));
    }

    @GetMapping
    public ResponseEntity<List<PermissionDTO>> getAllPermissions() {
        return ResponseEntity.ok( permissionService.listPermissions());

    }
    @GetMapping("/{id}")
    public ResponseEntity<PermissionDTO> getByid(@PathVariable Long id) {
      return   ResponseEntity.ok(permissionService.getPermissionById(id));
    }


    @GetMapping("/{label}/actions")
    public ResponseEntity<List<ActionPermission>> getActions(@PathVariable String label) {
        return ResponseEntity.ok(permissionService.getActionsByLabel(label));
    }
}
