package com.medecineWebApp.Inventory_Service.service;

import com.medecineWebApp.Inventory_Service.dto.MedicalConsumableDTO;
import com.medecineWebApp.Inventory_Service.models.MedicalConsumable;

import java.util.List;

public interface MedicalConsumableService {
    List<MedicalConsumableDTO> getAllConsumables();
    MedicalConsumableDTO addConsumable( MedicalConsumable consumable);
}
