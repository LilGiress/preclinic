package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.ExperienceDTO;
import com.medecineWebApp.Employees.models.Experience;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    ExperienceDTO toExperience(Experience experience);
    @InheritInverseConfiguration
    Experience fromExperienceDTO(ExperienceDTO experienceDTO);
}
