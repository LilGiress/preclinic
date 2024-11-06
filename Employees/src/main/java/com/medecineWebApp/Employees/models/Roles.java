package com.medecineWebApp.Employees.models;


import com.medecineWebApp.Employees.enums.PermissionType;
import com.medecineWebApp.Employees.enums.RoleType;

import java.util.List;
import java.util.Set;

public class Roles {
    private Long id;
    private RoleType name;
    private Set<PermissionType> permissions;

    public Roles(RoleType name, Set<PermissionType> permissions, List<Groupe> groups) {
        this.name = name;
        this.permissions = permissions;
        //this.groups = groups;
    }

    public Roles(RoleType name, Set<PermissionType> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public Roles() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public Set<PermissionType> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionType> permissions) {
        this.permissions = permissions;
    }

   /* public List<Groupe> getGroups() {
        return groups;
    }

    public void setGroups(List<Groupe> groups) {
        this.groups = groups;
    }*/
}
