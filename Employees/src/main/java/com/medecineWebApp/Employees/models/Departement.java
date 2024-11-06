package com.medecineWebApp.Employees.models;


import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Departement  {

    private Long id;
    private String name;
    private String description;
    private boolean active;

    private Set<Roles> roles;


}
