package com.medecineWebApp.Employees.controller;

import com.medecineWebApp.Employees.enums.AttendanceStatus;
import com.medecineWebApp.Employees.models.Attendance;
import com.medecineWebApp.Employees.models.Employee;
import com.medecineWebApp.Employees.services.AttendanceService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;


@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
    @PostMapping("/mark")
    public Attendance markAttendance(
            @RequestParam Long employeeId,
            @RequestParam LocalDate date,
            @RequestParam LocalTime checkInTime,
            @RequestParam LocalTime checkOutTime,
            @RequestParam AttendanceStatus status
    ) {
        return attendanceService.MakeAttendance(employeeId, date, checkInTime, checkOutTime, status);
    }

    @GetMapping("/attendance/{employeeId}")
    public Page<Attendance> getAttendanceByEmployeeId(@PathVariable Long employeeId,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        return attendanceService.findAttendanceByEmployeeId(employeeId, page, size);
    }

    @GetMapping("/attendance/search")
    public Page<Attendance> searchAttendance(
            @RequestParam Employee employeeId,
            @RequestParam int month,
            @RequestParam int year,
            @RequestParam String date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        return attendanceService.findAttendanceByEmployeeIdAndMonthAndYearAndDate(employeeId,month, year,date,page,size);
    }
}
