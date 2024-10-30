package com.medecineWebApp.Employees.repository;


import com.medecineWebApp.Employees.models.doctors.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
    List<DoctorSchedule> findByDoctorIdAndDate(Long doctorId, LocalDate date);
}
