package com.medecineWebApp.Employees.dto;

import com.medecineWebApp.Employees.enums.AttendanceStatus;
import com.medecineWebApp.Employees.models.Employee;
import jakarta.persistence.*;
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
public class AttendanceDTO extends AuditableDTO{
    private Long id;

    private EmployeeDTO employee;

    private LocalDate attendanceDate;
    private LocalTime checkInTime;

    private LocalTime checkOutTime;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus present;
}
