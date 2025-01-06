package com.medecineWebApp.patients.models;


import com.medecineWebApp.Employees.enums.EntityStatus;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Departement {

    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private EntityStatus status;

    private List<Services> services = new ArrayList<>();

    // Un département peut contenir plusieurs rôles
    @JoinColumn(name = "department_id")
    private Set<Roles> roles;

    @Transient
    private List<Doctor> doctors;

    @Transient
    private List<Employee> employees;
    private Long leaveId;


}
