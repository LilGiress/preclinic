package com.medecineWebApp.Configuration.models;


import com.medecineWebApp.Configuration.enums.EmployeeStatus;
import com.medecineWebApp.Configuration.enums.Gender;
import jakarta.persistence.*;
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

public class Patient  {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Gender gender;
    private LocalDate birthDate;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private EmployeeStatus status;
    private String ImageUrl;

    private List<Treatment> treatments;

    private List<Appointment> appointments;



}
