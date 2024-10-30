package com.medecineWebApp.patients.repository;

import com.medecineWebApp.patients.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
