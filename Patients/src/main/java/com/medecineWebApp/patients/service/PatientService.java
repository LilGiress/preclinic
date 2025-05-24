package com.medecineWebApp.patients.service;

import com.medecineWebApp.patients.dto.PatientDTO;
import com.medecineWebApp.patients.models.Patient;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PatientService {
    Page<PatientDTO> getAllPatients(int page, int size,Long doctorId);
  PatientDTO getPatient(Long id);
    PatientDTO createPatient(Patient patient);
    PatientDTO updatePatient(Long id,Patient patient);
    void deletePatient(Long id);
    Long countPatientsByDoctor(Long doctorId);
    Long countTodayPatientsByDoctor(Long doctorId);

}
