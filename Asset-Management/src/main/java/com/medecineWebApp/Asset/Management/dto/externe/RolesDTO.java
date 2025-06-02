package com.medecineWebApp.Asset.Management.dto.externe;

import lombok.*;

import java.security.Permission;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RolesDTO {
    private Long id;

    private String name;

    private Set<Permission> permissions = new HashSet<>();
}
