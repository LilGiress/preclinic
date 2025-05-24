package com.medecineWebApp.Configuration.payload.request;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolesRequest {
    private String name;
    private Set<PermissionRequest> permissions;
}
