package com.medecineWebApp.Inventory_Service.service.impl;

import com.medecineWebApp.Inventory_Service.dto.PrescriptionDTO;
import com.medecineWebApp.Inventory_Service.exception.PrescriptionNotFoundException;
import com.medecineWebApp.Inventory_Service.mapper.PrescriptionMapper;
import com.medecineWebApp.Inventory_Service.models.Prescription;
import com.medecineWebApp.Inventory_Service.models.PrescriptionItem;
import com.medecineWebApp.Inventory_Service.repository.PrescriptionItemRepository;
import com.medecineWebApp.Inventory_Service.repository.PrescriptionRepository;
import com.medecineWebApp.Inventory_Service.service.PrescriptionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final PrescriptionItemRepository prescriptionItemRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, PrescriptionMapper prescriptionMapper,  PrescriptionItemRepository prescriptionItemRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionMapper = prescriptionMapper;
        this.prescriptionItemRepository = prescriptionItemRepository;
    }

    @Override
    public PrescriptionDTO createPrescription(Prescription prescription) {
        if(prescription.getDoctorId() == null) {
            throw new RuntimeException("Docteur non trouvé");
        }
        prescription.setPatientId(prescription.getPatientId());
        prescription.setDoctorId(prescription.getDoctorId());
        prescription.setIssuedAt(LocalDateTime.now());
        return prescriptionMapper.toPrescriptionDTO(prescriptionRepository.save(prescription));
    }

    @Override
    public List<PrescriptionDTO> findAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();
        return prescriptions.stream()
                .map(prescriptionMapper::toPrescriptionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDTO> getPrescriptionsByPatient(Long patientId) {
        List<Prescription> prescriptions =prescriptionRepository.findByPatientId(patientId);
        return prescriptions.stream()
                .map(prescriptionMapper::toPrescriptionDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PrescriptionDTO addPrescriptionItem(Long prescriptionId, PrescriptionItem item) {
        // Récupérer la prescription
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new PrescriptionNotFoundException("Prescription introuvable avec l'ID: " + prescriptionId));

        // Associer l'item à la prescription
        item.setPrescription(prescription);

        // Sauvegarder l'item
        prescriptionItemRepository.save(item);

        // Ajouter l'item à la liste et sauvegarder la prescription mise à jour
        prescription.getItems().add(item);
        return prescriptionMapper.toPrescriptionDTO(prescriptionRepository.save(prescription));
    }
}
