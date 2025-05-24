package com.medecineWebApp.Employees.services.impl;

import com.medecineWebApp.Employees.dto.MedicalRecordDTO;
import com.medecineWebApp.Employees.mapper.MedicalRecordMapper;
import com.medecineWebApp.Employees.models.MedicalRecord;
import com.medecineWebApp.Employees.repository.MedicalRecordRepository;
import com.medecineWebApp.Employees.services.MedicalRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository, MedicalRecordMapper medicalRecordMapper) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicalRecordMapper = medicalRecordMapper;
    }

    @Override
    public MedicalRecordDTO save(MedicalRecord medicalRecord) {
        return medicalRecordMapper.toMedicalRecordDTO(medicalRecordRepository.save(medicalRecord));
    }

    @Override
    public List<MedicalRecordDTO> findMedicalRecordsByPatientId(Long patientId) {
        return medicalRecordRepository.findAll()
                .stream()
                .filter(medicalRecord -> medicalRecord.getPatientId().equals(patientId))
                .map(medicalRecordMapper::toMedicalRecordDTO)
                .toList();
    }

    @Override
    public List<MedicalRecordDTO> findMedicalRecordByDoctorId(Long doctorId) {
        return medicalRecordRepository.findAll()
                .stream()
                .filter(medicalRecord -> medicalRecord.getDoctor().getId().equals(doctorId))
                .map(medicalRecordMapper::toMedicalRecordDTO)
                .toList();
    }
}
