package com.medecineWebApp.Employees.events;

import com.medecineWebApp.Employees.enums.EventType;
import com.medecineWebApp.Employees.models.Departement;
import com.medecineWebApp.Employees.models.Roles;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorEvent {
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
}
