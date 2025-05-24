package com.medecineWebApp.Employees.services;

import com.medecineWebApp.Employees.dto.MedicalRecordDTO;
import com.medecineWebApp.Employees.models.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {
MedicalRecordDTO save(MedicalRecord medicalRecord);
List<MedicalRecordDTO> findMedicalRecordsByPatientId(Long patientId);
List<MedicalRecordDTO> findMedicalRecordByDoctorId(Long doctorId);
}
