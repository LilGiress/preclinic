package com.medecineWebApp.Employees.services.impl;

import com.medecineWebApp.Employees.enums.AttendanceStatus;
import com.medecineWebApp.Employees.filter.AttendanceSpecifications;
import com.medecineWebApp.Employees.models.Attendance;
import com.medecineWebApp.Employees.models.Employee;
import com.medecineWebApp.Employees.repository.AttendanceRepository;
import com.medecineWebApp.Employees.repository.EmployeeRepository;
import com.medecineWebApp.Employees.services.AttendanceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Attendance save(Attendance attendance) {
        return null;
    }

    @Override
    public Attendance MakeAttendance(Long employeeId, LocalDate attendanceDate, LocalTime checkInTime, LocalTime checkOutTime, AttendanceStatus status) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            Attendance attendance = new Attendance();
            attendance.setAttendanceDate(attendanceDate);
            attendance.setCheckInTime(checkInTime);
            attendance.setCheckOutTime(checkOutTime);
            attendance.setPresent(status);
            attendance.setEmployee(employee.get());
            return attendanceRepository.save(attendance);
        } else {
            throw new RuntimeException("Employee not found with ID: " + employeeId);
        }
    }

    @Override
    public Page<Attendance> findAttendanceByEmployeeId(Long employeeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return attendanceRepository.findByEmployeeId(employeeId, pageable);
    }

    @Override
    public Page<Attendance> findAttendanceByEmployeeIdAndMonthAndYearAndDate(Employee employeeId, int month, int year, String date, int page, int size) {

        Specification<Attendance> specification = Specification.where(
                AttendanceSpecifications.hasEmployeId(employeeId))
                .and(AttendanceSpecifications.attendanceDate(date))
                .and(AttendanceSpecifications.hasMonth(month))
                .and(AttendanceSpecifications.hasYear(year));
        Pageable pageable = PageRequest.of(page, size);
        return attendanceRepository.findAll(specification, pageable);
    }

}
