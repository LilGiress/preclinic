package com.medecineWebApp.Configuration.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medecineWebApp.Configuration.models.role.ActionPermission;
import com.medecineWebApp.Configuration.models.role.Permission;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.repository.permission.PermissionRepository;
import com.medecineWebApp.Configuration.repository.role.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
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
        if (roleRepository.count() > 0) {
            System.out.println("⚠️ Roles déjà présents, import ignoré.");
            return;
        }
        InputStream inputStream = new ClassPathResource("data/roles_with_detailed_permissions.json").getInputStream();
        List<Map<String, Object>> rolesData = objectMapper.readValue(inputStream, new TypeReference<>() {});

        for (Map<String, Object> roleMap : rolesData) {
            Roles role = new Roles();
            role.setName((String) roleMap.get("name"));
            role.setDescription((String) roleMap.get("description"));

            List<Permission> permissions = new ArrayList<>();
            List<Map<String, Object>> permissionsData = (List<Map<String, Object>>) roleMap.get("permissions");

            if (permissionsData != null) {
                for (Map<String, Object> permMap : permissionsData) {
                    Permission permission = new Permission();
                    permission.setLabel((String) permMap.get("label"));
                    permission.setRole(role);

                    List<ActionPermission> actions = new ArrayList<>();
                    List<Map<String, Object>> actionsData = (List<Map<String, Object>>) permMap.get("actions");

                    if (actionsData != null) {
                        for (Map<String, Object> actionMap : actionsData) {
                            ActionPermission action = new ActionPermission();
                            action.setLabel((String) actionMap.get("label"));
                            action.setSelected(Boolean.TRUE.equals(actionMap.get("isSelected")));
                            action.setPermission(permission);
                            actions.add(action);
                        }
                    }

                    permission.setActions(actions);
                    permissions.add(permission);
                }
            }

            role.setPermissions(permissions);
            roleRepository.save(role);
        }

        System.out.println("✅ Rôles et permissions importés avec succès !");

    }
}
