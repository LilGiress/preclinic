package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.enums.EntityStatus;
import com.medecineWebApp.Configuration.models.role.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartementDTO {
    private Long id;
    private String name;
    private String description;
    private EntityStatus status;
    private Set<Roles> roles;
}
