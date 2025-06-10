package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.MedicalFileDTO;
import com.medecineWebApp.Configuration.models.MedicalFile;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MedicalFileMapper {
    MedicalFileDTO entityToDto(MedicalFile medicalFile);
    @InheritInverseConfiguration
    MedicalFile dtoToEntity(MedicalFileDTO medicalFileDTO);
}
