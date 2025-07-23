package com.medecineWebApp.Inventory_Service.mapper;

import com.medecineWebApp.Inventory_Service.dto.MedicalConsumableDTO;
import com.medecineWebApp.Inventory_Service.models.MedicalConsumable;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicalConsumableMapper {
    MedicalConsumableDTO toMedicalConsumableDTO(MedicalConsumable consumable);
    @InheritInverseConfiguration
    MedicalConsumable toMedicalConsumable(MedicalConsumableDTO consumableDTO);
}
