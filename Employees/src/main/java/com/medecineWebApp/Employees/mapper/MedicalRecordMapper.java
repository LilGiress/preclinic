package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.MedicalRecordDTO;
import com.medecineWebApp.Employees.models.MedicalRecord;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    MedicalRecordDTO toMedicalRecordDTO(MedicalRecord medicalRecord);
    @InheritInverseConfiguration
    MedicalRecord toMedicalRecord(MedicalRecordDTO medicalRecordDTO);
}
