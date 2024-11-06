package com.medecineWebApp.Employees.dto;

import com.medecineWebApp.Employees.enums.ScheduleStatus;
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
public class DoctorScheduleDTO {
    private Long id;

    private Long doctorId;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private EnumSet<DayOfWeek> availableDays;

    private String message;

    private ScheduleStatus status;
}
