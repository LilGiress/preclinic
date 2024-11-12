package com.medecineWebApp.Employees.models;


import com.medecineWebApp.Employees.enums.AppointmentStatus;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "doctors_appointment")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String appointmentCode;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Transient
    @Column(nullable = false)
    private Patient patientId;

    @Column(nullable = false)
    private Long departmentId;

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
