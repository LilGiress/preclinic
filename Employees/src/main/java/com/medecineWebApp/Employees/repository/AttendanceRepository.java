package com.medecineWebApp.Employees.repository;

import com.medecineWebApp.Employees.models.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {
    Page<Attendance> findByEmployeeId(Long employeeId, Pageable pageable);
}
