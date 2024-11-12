package com.medecineWebApp.Configuration.models;



import com.medecineWebApp.Configuration.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Appointment  {

    private Long id;

    private String appointmentCode;

    private Doctor doctor;

    private Patient patientId;

    private Long departmentId;

    private String PatientPhoneNumber;

    private String message;

    private String email;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private AppointmentStatus status;
}
