package com.medecineWebApp.Employees.dto;

import com.medecineWebApp.Employees.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private String appointmentCode;

    private Long doctorId;

    private Long patientId;

    private Long departmentId;

    private String PatientPhoneNumber;

    private String message;

    private String email;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private AppointmentStatus status;
}
