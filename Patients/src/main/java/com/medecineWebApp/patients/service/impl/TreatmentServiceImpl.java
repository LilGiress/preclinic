package com.medecineWebApp.patients.service.impl;

import com.medecineWebApp.patients.dto.TreatmentDTO;
import com.medecineWebApp.patients.enums.TreatmentStatus;
import com.medecineWebApp.patients.filter.TreatmentSpecifications;
import com.medecineWebApp.patients.mapper.TreatmentMapper;
import com.medecineWebApp.patients.models.Treatment;
import com.medecineWebApp.patients.repository.TreatmentRepository;
import com.medecineWebApp.patients.service.TreatmentService;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TreatmentServiceImpl implements TreatmentService {
    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;


    public TreatmentServiceImpl(TreatmentRepository treatmentRepository, TreatmentMapper treatmentMapper, PeerAwareInstanceRegistry peerAwareInstanceRegistry) {
        this.treatmentRepository = treatmentRepository;
        this.treatmentMapper = treatmentMapper;
    }



    @Override
    public Page<TreatmentDTO> getAllTreatmentsByPatientId(Long patientId, Long doctorId, TreatmentStatus status, String description, LocalDate date, int page, int size) {
        Specification<Treatment> specification = Specification.where(
                TreatmentSpecifications.treatmentNameContains(description)
                        .and(TreatmentSpecifications.hasDate(date))
                        .and(TreatmentSpecifications.hasDoctorId(doctorId))
                        .and(TreatmentSpecifications.hasStatus(status))
        );

        Pageable pageable = PageRequest.of(page, size);

        Page<Treatment> treatments = treatmentRepository.findAll(specification,pageable);
        return treatmentMapper.treatmentsToTreatmentDTOs(treatments);
    }

    @Override
    public TreatmentDTO createTreatment(Treatment treatment) {
        return treatmentMapper.treatmentToTreatmentDTO(treatmentRepository.save(treatment));
    }

    @Override
    public TreatmentDTO updateTreatment(Long id, Treatment treatment) {
        Optional<Treatment> treatmentOptional = treatmentRepository.findById(id);
        if (treatmentOptional.isPresent()) {
            Treatment treatmentToUpdate = treatmentOptional.get();
            treatmentToUpdate.setDescription(treatment.getDescription());
            treatmentToUpdate.setStatus(treatment.getStatus());
            treatmentToUpdate.setDoctorId(treatment.getDoctorId());
            treatmentToUpdate.setPatient(treatment.getPatient());
            treatmentToUpdate.setStartDate(treatment.getStartDate());
            treatmentToUpdate.setEndDate(treatment.getEndDate());
            return treatmentMapper.treatmentToTreatmentDTO(treatmentRepository.save(treatmentToUpdate));
        }
       throw new RuntimeException("Treatment with id " + id + " not found");
    }

    @Override
    public Optional<TreatmentDTO> getTreatment(Long id) {
        Optional<Treatment> treatmentOptional = treatmentRepository.findById(id);
        return treatmentMapper.OPTIONAL_TREATMENT_DTO(treatmentOptional);
    }

    @Override
    public void deleteTreatment(Long id) {
        treatmentRepository.deleteById(id);

    }
}
