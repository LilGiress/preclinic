package com.medecineWebApp.patients.mapper;

import com.medecineWebApp.patients.dto.PatientDTO;
import com.medecineWebApp.patients.models.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientMapper INSTANCE= Mappers.getMapper(PatientMapper.class);
    PatientDTO patientToPatientDTO(Patient patient);
    Patient patientDTOToPatient(PatientDTO patientDTO);
}
