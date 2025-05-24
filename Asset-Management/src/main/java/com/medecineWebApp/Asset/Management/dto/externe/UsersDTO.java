package com.medecineWebApp.Asset.Management.dto.externe;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
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

    private Set<Roles> roles = new HashSet<>();

    private List<Long> departments;

    private Long employeeId;

//    @JsonIgnore
//    @Transient
//    private Employee employee;
}
