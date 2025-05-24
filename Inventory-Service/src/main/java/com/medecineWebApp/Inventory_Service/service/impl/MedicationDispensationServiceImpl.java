package com.medecineWebApp.Inventory_Service.service.impl;

import com.medecineWebApp.Inventory_Service.models.MedicationDispensation;
import com.medecineWebApp.Inventory_Service.models.PrescriptionItem;
import com.medecineWebApp.Inventory_Service.repository.MedicationDispensationRepository;
import com.medecineWebApp.Inventory_Service.repository.PrescriptionItemRepository;
import com.medecineWebApp.Inventory_Service.service.MedicationDispensationService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicationDispensationServiceImpl implements MedicationDispensationService {
    private final MedicationDispensationRepository medicationDispensationRepository;
    private final StockServiceImpl stockService;
    private final PrescriptionItemRepository prescriptionItemRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MedicationDispensationServiceImpl(MedicationDispensationRepository medicationDispensationRepository, StockServiceImpl stockService, PrescriptionItemRepository prescriptionItemRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.medicationDispensationRepository = medicationDispensationRepository;
        this.stockService = stockService;
        this.prescriptionItemRepository = prescriptionItemRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void dispenseMedication(Long prescriptionId) {
        List<PrescriptionItem> items = prescriptionItemRepository.findByPrescriptionId(prescriptionId);

        for (PrescriptionItem item : items) {
            // Vérifier le stock
            stockService.useStock(item.getConsumable().getId(), item.getQuantity());

            // Enregistrer la distribution
            MedicationDispensation dispensation = new MedicationDispensation();
            dispensation.setPatientId(item.getPrescription().getPatientId());
            dispensation.setDoctorId(item.getPrescription().getDoctorId());
            dispensation.setConsumable(item.getConsumable());
            dispensation.setQuantityDispensed(item.getQuantity());
            dispensation.setDispensedAt(LocalDateTime.now());
            medicationDispensationRepository.save(dispensation);
        }
        // Envoyer une notification au patient via Kafka
        kafkaTemplate.send("medication-dispensed", "📢 Médicaments prêts pour le patient " + prescriptionId);
    }


}
