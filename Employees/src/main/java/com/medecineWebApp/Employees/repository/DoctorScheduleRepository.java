package com.medecineWebApp.Employees.repository;


import com.medecineWebApp.Employees.models.doctors.DoctorSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
    Page<DoctorSchedule> findByDoctorIdAndDate(Long doctorId, LocalDate date, Pageable pageable);
}
