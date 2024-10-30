package com.medecineWebApp.Configuration.utilis;

import org.springframework.security.core.GrantedAuthority;

public class PermissionGrantedAuthority implements GrantedAuthority {
    private final String permission;

    public PermissionGrantedAuthority(String permission) {
        this.permission = permission;
    }


    @Override
    public String getAuthority() {
        return this.permission;
    }
}
