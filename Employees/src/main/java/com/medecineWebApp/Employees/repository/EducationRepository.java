package com.medecineWebApp.Employees.repository;

import com.medecineWebApp.Employees.models.Education;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByProfile(Doctor profile);
}
