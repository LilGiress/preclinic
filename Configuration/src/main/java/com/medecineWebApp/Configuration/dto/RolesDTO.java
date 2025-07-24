package com.medecineWebApp.Configuration.dto;



import com.medecineWebApp.Configuration.models.role.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RolesDTO extends AuditableDTO {
    private Long id;
    private String name;
    private String description;
    private Set<Permission> permissions;
}
