package com.medecineWebApp.Configuration.dto;


import com.medecineWebApp.Configuration.models.role.ActionPermission;
import com.medecineWebApp.Configuration.models.role.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO extends AuditableDTO {
    private Long id;
    private String label;
    private String description;
    private boolean isSelected;
    private boolean disabled;
    private List<ActionPermission> actions;
    private RolesDTO role;

}
