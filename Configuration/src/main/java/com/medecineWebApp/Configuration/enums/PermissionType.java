package com.medecineWebApp.Configuration.enums;


import lombok.Getter;

@Getter

public enum PermissionType {

    ADMIN_READ("admin_read"),
    ADMIN_UPDATE("admin_update"),
    ADMIN_CREATE("admin_create"),
    ADMIN_DELETE("admin_delete"),

    MANAGER_READ("manager_read"),
    MANAGER_UPDATE("manager_update"),
    MANAGER_CREATE("manager_create"),
    MANAGER_DELETE("manager_delete"),

    DOCTOR_READ("doctor_read"),
    DOCTOR_UPDATE("doctor_update"),
    DOCTOR_CREATE("doctor_create"),
    DOCTOR_DELETE("doctor_delete"),


    NURSE_READ("nurse_read"),
    NURSE_UPDATE("nurse_update"),
    NURSE_CREATE("nurse_create"),
    NURSE_DELETE("nurse_delete"),


    ACCOUNTANT_READ("accountant_read"),
    ACCOUNTANT_UPDATE("accountant_update"),
    ACCOUNTANT_CREATE("accountant_create"),
    ACCOUNTANT_DELETE("accountant_delete"),


    USER_CREATE("user_create"),
    USER_UPDATE("user_update"),
    USER_READ("user_read");

    public final String permission;
    PermissionType(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
