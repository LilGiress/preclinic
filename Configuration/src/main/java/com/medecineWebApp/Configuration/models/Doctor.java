package com.medecineWebApp.Configuration.models;


import com.medecineWebApp.Configuration.enums.EmployeeStatus;
import com.medecineWebApp.Configuration.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Doctor {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String doctorBirthday;
    private String imageUrl;
    private String biography;
    private String doctorPostalCode;
    private String doctorCountry;
    private EmployeeStatus status;
    private Gender gender;
    private String address;
    private Departement department;
    private List<Appointment> appointments;





}
