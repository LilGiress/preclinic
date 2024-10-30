package com.medecineWebApp.patients.service;

import com.medecineWebApp.patients.dto.PatientDTO;
import com.medecineWebApp.patients.models.Patient;
import org.springframework.data.domain.Page;


public interface PatientService {
    Page<Patient> getAllPatients(int page, int size);
  PatientDTO getPatient(Long id);
    PatientDTO createPatient(Patient patient);
    PatientDTO updatePatient(Long id,Patient patient);
    void deletePatient(Long id);
}
