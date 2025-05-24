package com.medecineWebApp.patients.mapper;

import com.medecineWebApp.patients.dto.TreatmentDTO;
import com.medecineWebApp.patients.models.Treatment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreatmentMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    TreatmentDTO treatmentToTreatmentDTO(Treatment treatment);
    @InheritInverseConfiguration
    Treatment treatmentDTOToTreatment(TreatmentDTO treatmentDTO);

}
