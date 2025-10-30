package com.medecineWebApp.Configuration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RolesDTO extends AuditableDTO {
    private Long id;
    private String name;
    private String description;
    private List<PermissionDTO> permissions;
}
