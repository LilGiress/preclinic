package com.medecineWebApp.patients.service;

import com.medecineWebApp.patients.dto.TreatmentDTO;
import com.medecineWebApp.patients.enums.TreatmentStatus;
import com.medecineWebApp.patients.models.Treatment;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Optional;

public interface TreatmentService {
    Page<TreatmentDTO> getAllTreatmentsByPatientId(Long patientId, Long doctorId, TreatmentStatus status, String description, LocalDate date, int page, int size);
    TreatmentDTO createTreatment(Treatment treatment);
    TreatmentDTO updateTreatment(Long id,Treatment treatment);
    Optional<TreatmentDTO> getTreatment(Long id);
    void deleteTreatment(Long id);
}
