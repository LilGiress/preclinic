package com.medecineWebApp.Configuration.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends AuditableDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean enabled;
    private boolean accountLocked;
    private String photoUrl; // Chemin ou URL de l'image
    private Set<RolesDTO> roles = new HashSet<>();

    private List<DepartementDTO> departments;

    private Long employeeId;

}
