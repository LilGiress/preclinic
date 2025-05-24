package com.medecineWebApp.Employees.repository;

import com.medecineWebApp.Employees.models.Experience;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByProfile(Doctor profile);
}
