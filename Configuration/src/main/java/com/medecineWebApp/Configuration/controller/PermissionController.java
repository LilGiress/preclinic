package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.PermissionDTO;
import com.medecineWebApp.Configuration.models.ModulePermission;
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
    @GetMapping("/create")
    public ResponseEntity<PermissionDTO> createPermission(@RequestBody List<Permission> permission) {
        return ResponseEntity.ok(permissionService.createPermission(permission));
    }

    @PutMapping("/update")
    public ResponseEntity<PermissionDTO> updatePermission(@RequestParam Long id, @RequestBody Permission permission) {
        return ResponseEntity.ok(permissionService.updatePermission(id, permission));
    }

    @GetMapping("/permissions")
    public ResponseEntity<Page<PermissionDTO>> getAllPermissions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok( permissionService.listPermissions(page, size));

    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        permissionService.deletePermission(id);
    }

    @GetMapping("/modules")
    public List<ModulePermission> getAllModules() {
        return permissionService.listModulePermissions();
    }

    @PostMapping("/module_permission")
    public ModulePermission createModulePermission(@RequestParam String moduleName, @RequestBody List<Long> permissionIds) {
        List<Permission> permissions = permissionService.listPermissions(permissionIds);
        return permissionService.createModulePermission(moduleName, permissions);
    }
}
