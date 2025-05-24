package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.SalarySettingsDTO;
import com.medecineWebApp.Configuration.models.setting.SalarySettings;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface SalarySettingsMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    SalarySettingsDTO salarySettingsDTOToSalarySettingsDTO(SalarySettings salarySettings);
    @InheritInverseConfiguration
    SalarySettings salarySettingsDTOToSalarySettings(SalarySettingsDTO salarySettingsDTO);
}
