package com.medecineWebApp.Inventory_Service.service;

import com.medecineWebApp.Inventory_Service.dto.PrescriptionDTO;
import com.medecineWebApp.Inventory_Service.models.Prescription;
import com.medecineWebApp.Inventory_Service.models.PrescriptionItem;

import java.util.List;

public interface PrescriptionService {
    PrescriptionDTO createPrescription(Prescription prescription);
    List<PrescriptionDTO> findAllPrescriptions();
    List<PrescriptionDTO> getPrescriptionsByPatient(Long patientId);
    PrescriptionDTO addPrescriptionItem(Long prescriptionId, PrescriptionItem item);

}
