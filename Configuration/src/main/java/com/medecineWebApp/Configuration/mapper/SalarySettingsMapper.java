package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.SalarySettingsDTO;
import com.medecineWebApp.Configuration.models.setting.SalarySettings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface SalarySettingsMapper {
    SalarySettingsMapper INSTANCE = Mappers.getMapper(SalarySettingsMapper.class);
    SalarySettingsDTO salarySettingsDTOToSalarySettingsDTO(SalarySettings salarySettings);
    SalarySettings salarySettingsDTOToSalarySettings(SalarySettingsDTO salarySettingsDTO);
    Page<SalarySettingsDTO> SalarySettingsDTOToSalarySettings(Page<SalarySettings> salarySettings);
}
