package com.medecineWebApp.Configuration.events;


import com.medecineWebApp.Configuration.enums.EventType;
import com.medecineWebApp.Configuration.models.Departement;
import com.medecineWebApp.Configuration.models.role.Roles;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEvent {
    private Long id;
    private Long userId;
    private String firstname;
    private String lastname;
    private String username;
    private EventType eventType;
    private String email;
    private String password;
    private Set<Roles> roles = new HashSet<>();
    private List<Departement> departments;
   // private Set<Groupe> groups;

}
