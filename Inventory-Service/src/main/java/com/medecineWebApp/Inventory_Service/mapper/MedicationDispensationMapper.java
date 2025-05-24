package com.medecineWebApp.Inventory_Service.mapper;

import com.medecineWebApp.Inventory_Service.dto.MedicationDispensationDTO;
import com.medecineWebApp.Inventory_Service.models.MedicationDispensation;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicationDispensationMapper {
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "lastModifiedBy", target = "lastModifiedBy")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    MedicationDispensationDTO toMedicationDispensationDTO(MedicationDispensation medicationDispensation);
    @InheritInverseConfiguration
    MedicationDispensation toMedicationDispensation(MedicationDispensationDTO medicationDispensationDTO);
}
