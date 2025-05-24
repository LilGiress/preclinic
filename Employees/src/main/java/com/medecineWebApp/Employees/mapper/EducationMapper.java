package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.EducationDTO;
import com.medecineWebApp.Employees.models.Education;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EducationMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    EducationDTO toEducation(Education education);
    @InheritInverseConfiguration
    Education fromEducationDTO(EducationDTO educationDTO);

}
