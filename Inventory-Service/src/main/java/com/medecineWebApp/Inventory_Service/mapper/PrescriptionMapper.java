package com.medecineWebApp.Inventory_Service.mapper;

import com.medecineWebApp.Inventory_Service.dto.PrescriptionDTO;
import com.medecineWebApp.Inventory_Service.models.Prescription;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {
    PrescriptionDTO toPrescriptionDTO(Prescription prescription);
    @InheritInverseConfiguration
    Prescription toPrescription(PrescriptionDTO prescriptionDTO);

}
