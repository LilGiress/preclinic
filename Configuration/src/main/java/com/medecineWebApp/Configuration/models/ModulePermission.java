package com.medecineWebApp.Configuration.models;

import com.medecineWebApp.Configuration.models.role.Permission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "config_Module_Permission")
public class ModulePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moduleName; // Ex: "Employee", "Holidays"

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "module_permission_mapping",
            joinColumns = @JoinColumn(name = "module_permission_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions;
}
