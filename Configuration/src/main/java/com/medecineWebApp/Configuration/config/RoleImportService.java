package com.medecineWebApp.Configuration.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medecineWebApp.Configuration.models.role.Permission;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.repository.permission.PermissionRepository;
import com.medecineWebApp.Configuration.repository.role.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class RoleImportService implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final ObjectMapper objectMapper;

    public RoleImportService(RoleRepository roleRepository, PermissionRepository permissionRepository, ObjectMapper objectMapper) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        InputStream inputStream = new ClassPathResource("data/roles_with_detailed_permissions.json").getInputStream();
        List<Map<String, Object>> rolesData = objectMapper.readValue(inputStream, new TypeReference<>() {});

        for (Map<String, Object> roleData : rolesData) {
            Roles role = new Roles();
            role.setName((String) roleData.get("name"));
            role.setDescription((String) roleData.get("description"));

            Map<String, Boolean> perms = (Map<String, Boolean>) roleData.get("permission");
            Permission permission = new Permission(
                    perms.getOrDefault("canRead", false),
                    perms.getOrDefault("canWrite", false),
                    perms.getOrDefault("canCreate", false),
                    perms.getOrDefault("canDelete", false),
                    perms.getOrDefault("canImport", false),
                    perms.getOrDefault("canExport", false),
                    perms.getOrDefault("canApprove", false),
                    perms.getOrDefault("canValidate", false),
                    perms.getOrDefault("canAssign", false),
                    perms.getOrDefault("canGenerateReport", false),
                    perms.getOrDefault("canActivate", false)
            );

            permission.setRole(role);
            role.getPermissions().add(permission);
            roleRepository.save(role);
        }
    }
}
