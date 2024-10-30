package com.medecineWebApp.Configuration.exception.enums;


import lombok.Getter;

@Getter

public enum Permission {

    ADMIN_READ("admin_read"),
    ADMIN_UPDATE("admin_update"),
    ADMIN_CREATE("admin_create"),
    ADMIN_DELETE("admin_delete"),

    MANAGER_READ("manager_read"),
    MANAGER_UPDATE("manager_update"),
    MANAGER_CREATE("manager_create"),
    MANAGER_DELETE("manager_delete"),

    USER_CREATE("user_create"),
    USER_UPDATE("user_update"),
    USER_READ("user_read");

    public final String permission;
    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
