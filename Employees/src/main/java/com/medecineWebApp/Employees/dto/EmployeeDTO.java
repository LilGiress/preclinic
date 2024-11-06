package com.medecineWebApp.Employees.dto;

import com.medecineWebApp.Employees.enums.EmployeeStatus;
import com.medecineWebApp.Employees.models.Attendance;
import com.medecineWebApp.Employees.models.Departement;
import com.medecineWebApp.Employees.models.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long Id;
    private Long userId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private LocalDate dateDebutEntreEnFonction;
    private Departement department;
    private String position;
    private EmployeeStatus status;
    private List<Attendance> attendanceRecords;
    private Set<Roles> roles = new HashSet<>();
    private LocalDateTime dateOfCreation;
    private LocalDateTime lastModifiedDate;
}
