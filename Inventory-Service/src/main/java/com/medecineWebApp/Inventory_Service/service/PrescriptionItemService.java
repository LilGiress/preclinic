package com.medecineWebApp.Inventory_Service.service;

import com.medecineWebApp.Inventory_Service.dto.PrescriptionDTO;
import com.medecineWebApp.Inventory_Service.models.PrescriptionItem;

import java.util.List;

public interface PrescriptionItemService {
    PrescriptionDTO createPrescriptionItem(PrescriptionItem prescriptionItem);
    List<PrescriptionItem> findAllPrescriptionItems();
}
