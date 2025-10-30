package com.medecineWebApp.Configuration.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medecineWebApp.Configuration.models.role.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionPermissionDTO{
    private Long id;
    private String label;       // Exemple: "CAN_READ", "CAN_WRITE"
    private boolean selected;
    @JsonIgnore
    private Permission permission;
}
