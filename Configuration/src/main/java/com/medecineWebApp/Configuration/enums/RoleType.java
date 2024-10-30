package com.medecineWebApp.Configuration.enums;


import lombok.Getter;

import java.util.Set;


@Getter
public enum RoleType {

    USER(Set.of(
            PermissionType.USER_CREATE,
            PermissionType.USER_UPDATE,
            PermissionType.USER_READ
    )),

    MANAGER(Set.of(
            PermissionType.MANAGER_CREATE,
            PermissionType.MANAGER_READ,
            PermissionType.MANAGER_UPDATE,
            PermissionType.MANAGER_DELETE
    )),

    DOCTOR(Set.of(
            PermissionType.DOCTOR_CREATE,
            PermissionType.DOCTOR_READ,
            PermissionType.DOCTOR_UPDATE,
            PermissionType.DOCTOR_DELETE
    )),

    ADMINISTRATOR(Set.of(
            PermissionType.ADMIN_CREATE,
            PermissionType.ADMIN_READ,
            PermissionType.ADMIN_UPDATE,
            PermissionType.ADMIN_DELETE,
            PermissionType.MANAGER_CREATE,
            PermissionType.MANAGER_READ,
            PermissionType.MANAGER_UPDATE,
            PermissionType.MANAGER_DELETE
    ));

    private final Set<PermissionType> permissions;

    RoleType(Set<PermissionType> permissions) {
        this.permissions = permissions;
    }

    public Set<PermissionType> getPermissions() {
        return permissions;
    }

   /* public List<SimpleGrantedAuthority> getAuthorities() {
        var grantedAuthorities =this.permissions
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }*/

}


