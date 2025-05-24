package com.medecineWebApp.patients.service.impl;

import com.medecineWebApp.patients.dto.PatientDTO;
import com.medecineWebApp.patients.exception.PatientNotFoundException;
import com.medecineWebApp.patients.filter.PatientSpecifications;
import com.medecineWebApp.patients.mapper.PatientMapper;
import com.medecineWebApp.patients.models.Patient;
import com.medecineWebApp.patients.repository.PatientRepository;
import com.medecineWebApp.patients.service.NotificationService;
import com.medecineWebApp.patients.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    private final NotificationService notificationService;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, NotificationService notificationService) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.notificationService = notificationService;
    }

    @Override
    public Page<PatientDTO> getAllPatients(int page, int size,Long doctorId) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Patient> specification= Specification.where(
                PatientSpecifications.hasDoctorId(doctorId)
                );
        return patientRepository.findAll(specification,pageable).map(patientMapper::patientToPatientDTO);

    }

    @Override
    public PatientDTO getPatient(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            return patientMapper.patientToPatientDTO(patient.orElse(null));
        }
        throw new PatientNotFoundException("Patient not found");

    }

    @Override
    public PatientDTO createPatient(Patient patient) {
        return patientMapper.patientToPatientDTO(patientRepository.save(patient));
    }

    @Override
    public PatientDTO updatePatient(Long id, Patient patient) {
        if (id!= null &&  patient.getAppointmentId() != null) {
            return patientMapper.patientToPatientDTO(patientRepository.save(patient));
        }
        throw new PatientNotFoundException("Patient not found");

    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Long countPatientsByDoctor(Long doctorId) {
        return patientRepository.countPatientsByDoctor(doctorId);
    }

    @Override
    public Long countTodayPatientsByDoctor(Long doctorId) {
        return patientRepository.countTodayPatientsByDoctor(doctorId);
    }
}
