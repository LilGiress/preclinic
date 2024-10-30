package com.medecineWebApp.Configuration.config;

import com.medecineWebApp.Configuration.enums.PermissionType;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.models.user.User;
import com.medecineWebApp.Configuration.repository.user.UserRepository;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return false;
        }

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);

        // Check if the user has the required permission
        String permissionStr = permission.toString();
        for (Roles role : user.getRoles()) {
            if (role.getPermissions().contains(PermissionType.valueOf(permissionStr))) {
                return true; // User has the required permission
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
