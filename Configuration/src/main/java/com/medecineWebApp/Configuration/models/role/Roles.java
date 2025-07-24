package com.medecineWebApp.Configuration.models.role;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.medecineWebApp.Configuration.utilis.PermissionDeserializer;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    @Getter
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonDeserialize(contentUsing = PermissionDeserializer.class)
    @JsonManagedReference
    private Set<Permission> permissions = new HashSet<>();

    public Roles(String name, Set<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

}
