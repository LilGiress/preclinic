package com.medecineWebApp.patients.mapper;

import com.medecineWebApp.patients.dto.PatientDTO;
import com.medecineWebApp.patients.models.Patient;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    PatientDTO patientToPatientDTO(Patient patient);
    @InheritInverseConfiguration
    Patient patientDTOToPatient(PatientDTO patientDTO);
}
