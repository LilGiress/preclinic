package com.medecineWebApp.Employees.dto;

import com.medecineWebApp.Employees.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO extends AuditableDTO{
    private Long Id;
    private Long userId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Long addressId;

    private Long cityId;
    private String state;
    private String zip;
    private Long countryId;

    private LocalDate dateDebutEntreEnFonction;
    private Long departmentId;

    private String position;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    // One-to-Many relationship with Attendance
    private List<AttendanceDTO> attendanceRecords;
    private Long roleId;

    private Long leaveId;

    private Long invoiceId;
}
