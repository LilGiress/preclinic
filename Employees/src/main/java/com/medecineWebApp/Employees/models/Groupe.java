package com.medecineWebApp.Employees.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Groupe  {
    private Long id;
    private String nom;
    private String description;
    private Departement departement;
    private Set<User> users;
    private Groupe parentGroupe;


}
