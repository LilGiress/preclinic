package com.medecineWebApp.patients.service.impl;

import com.medecineWebApp.patients.dto.PatientDTO;
import com.medecineWebApp.patients.mapper.PatientMapper;
import com.medecineWebApp.patients.models.Patient;
import com.medecineWebApp.patients.repository.PatientRepository;
import com.medecineWebApp.patients.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public Page<Patient> getAllPatients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return patientRepository.findAll(pageable);
    }

    @Override
    public PatientDTO getPatient(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            return patientMapper.patientToPatientDTO(patient.orElse(null));
        }
        throw new RuntimeException("Patient not found");

    }

    @Override
    public PatientDTO createPatient(Patient patient) {
        return patientMapper.patientToPatientDTO(patientRepository.save(patient));
    }

    @Override
    public PatientDTO updatePatient(Long id, Patient patient) {
       Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            Patient update = patientOptional.get();
            update.setAddress(patient.getAddress());
            update.setCity(patient.getCity());
            update.setCountry(patient.getCountry());
            update.setEmail(patient.getEmail());
            update.setGender(patient.getGender());
            update.setBirthDate(patient.getBirthDate());
            update.setDateOfCreation(update.getDateOfCreation());
            update.setZip(patient.getZip());
            update.setPhone(patient.getPhone());
            update.setStatus(patient.getStatus());
            update.setState(patient.getState());
            update.setPassword(patient.getPassword());
            update.setLastName(patient.getLastName());
            update.setFirstName(patient.getFirstName());
            return patientMapper.patientToPatientDTO(patientRepository.save(update));
        }
        throw new RuntimeException("Patient not found");

    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
