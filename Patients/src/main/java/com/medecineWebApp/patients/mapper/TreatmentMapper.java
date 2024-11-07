package com.medecineWebApp.patients.mapper;

import com.medecineWebApp.patients.dto.TreatmentDTO;
import com.medecineWebApp.patients.models.Treatment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TreatmentMapper {
    TreatmentMapper INSTANCE = Mappers.getMapper(TreatmentMapper.class);
    TreatmentDTO treatmentToTreatmentDTO(Treatment treatment);
    Treatment treatmentDTOToTreatment(TreatmentDTO treatmentDTO);
    Optional<TreatmentDTO> OPTIONAL_TREATMENT_DTO(Optional<Treatment> treatment);
    Page<TreatmentDTO> treatmentsToTreatmentDTOs(Page<Treatment> treatments);

}
