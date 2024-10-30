package com.medecineWebApp.Configuration.models.role;

import com.medecineWebApp.Configuration.enums.PermissionType;
import com.medecineWebApp.Configuration.enums.RoleType;
import com.medecineWebApp.Configuration.models.Groupe;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private RoleType name;


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<PermissionType> permissions;


    /*@ManyToMany(mappedBy = "roles")
    private List<Groupe> groups;*/


    
    public Roles( RoleType name, Set<PermissionType> permissions, List<Groupe> groups) {
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
