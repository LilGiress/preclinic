package com.medecineWebApp.patients.models;



import com.medecineWebApp.patients.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    private Long id;

    private String appointmentCode;

    private Doctor doctor;

    private Patient patient;

    private Long departmentId;

    private String PatientPhoneNumber;

    private String message;

    private String email;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;
    private AppointmentStatus status;
}
