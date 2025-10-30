package com.medecineWebApp.Configuration.models.role;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.medecineWebApp.Configuration.utilis.PermissionDeserializer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonDeserialize(contentUsing = PermissionDeserializer.class)
    @JsonManagedReference
    private List<Permission> permissions = new ArrayList<>();

    public Roles(String name, List<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public void addPermission(Permission permission) {
        permissions.add(permission);
        permission.setRole(this);
    }
}
