package com.medecineWebApp.Configuration.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO {
    private Long id;
    private String label;
    private String description;
    private List<ActionPermissionDTO> actions;
    private RolesDTO role;

}
