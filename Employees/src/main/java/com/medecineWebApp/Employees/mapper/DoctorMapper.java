package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.DoctorDTO;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    DoctorDTO doctorToDTO(Doctor doctor);
    @InheritInverseConfiguration
    Doctor doctorDTOToDoctor(DoctorDTO doctorDTO);



}
