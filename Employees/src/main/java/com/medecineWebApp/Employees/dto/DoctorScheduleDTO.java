package com.medecineWebApp.Employees.dto;

import com.medecineWebApp.Employees.enums.ScheduleStatus;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.EnumSet;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorScheduleDTO extends AuditableDTO{
    private Long id;

    private DoctorDTO doctor;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @ElementCollection(targetClass = DayOfWeek.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "schedule_available_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @Enumerated(EnumType.STRING)
    private EnumSet<DayOfWeek> availableDays;

    private String message;

    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;
}
