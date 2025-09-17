package com.medecineWebApp.Configuration.config;

import com.medecineWebApp.Configuration.models.role.ActionPermission;
import com.medecineWebApp.Configuration.models.role.Permission;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.models.user.Users;
import com.medecineWebApp.Configuration.repository.user.UserRepository;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    private final UserRepository userRepository;

    public CustomPermissionEvaluator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails) || !(permission instanceof String)) {
            return false;
        }

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Check if the user has the required permission
//        String permissionStr = permission.toString();
//        for (Roles role : user.getRoles()) {
//            if (role.getPermissions().contains(PermissionType.valueOf(permissionStr))) {
//                return true; // User has the required permission
//            }
//        }

        String module = targetDomainObject.toString(); // Nom du module (ex: "PATIENTS")
        String action = permission.toString(); // Action demandée (ex: "READ")
        return hasPermission(username, module, action);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

    public boolean hasPermission(String username, String module, String action) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        for (Roles role : user.getRoles()) {
            for (Permission permission : role.getPermissions()) {
                // Vérifie si c'est le bon module
                if (permission.getLabel().equalsIgnoreCase(module)) {
                    // Vérifie si l'action demandée existe et est sélectionnée
                    for (ActionPermission actionPermission : permission.getActions()) {
                        if (actionPermission.getLabel().equalsIgnoreCase(action)
                                && actionPermission.isSelected()
                                && !actionPermission.isDisabled()) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
