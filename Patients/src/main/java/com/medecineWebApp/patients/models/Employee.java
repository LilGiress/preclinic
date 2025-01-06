package com.medecineWebApp.patients.models;

import com.medecineWebApp.Configuration.enums.EmployeeStatus;
import com.medecineWebApp.Configuration.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long Id;
    private User userId;
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


}
