package com.medecineWebApp.Employees.dto;

import com.medecineWebApp.Employees.enums.AppointmentStatus;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO extends AuditableDTO {
    private Long id;
    @Column(unique = true, nullable = false)
    private String appointmentCode;

    private DoctorDTO doctor;

    private Long patientId;

    private Long departementId;

    // Liste des IDs des patients suivis par ce docteur
    @ElementCollection
    private List<Long> treatmentIds = new ArrayList<>();

    @Column(nullable = false)
    private String PatientPhoneNumber;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false)
    private LocalTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
