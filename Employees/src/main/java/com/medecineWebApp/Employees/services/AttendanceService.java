package com.medecineWebApp.Employees.services;

import com.medecineWebApp.Employees.enums.AttendanceStatus;
import com.medecineWebApp.Employees.models.Attendance;
import com.medecineWebApp.Employees.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AttendanceService {
    Attendance save(Attendance attendance);
    Attendance MakeAttendance(Long employeeId, LocalDate attendanceDate, LocalTime checkInTime, LocalTime checkOutTime, AttendanceStatus status);
    Page<Attendance> findAttendanceByEmployeeId(Long employeeId, int page, int size);
    Page<Attendance> findAttendanceByEmployeeIdAndMonthAndYearAndDate(Employee employeeId, int month, int year, String date, int page, int size);
}
