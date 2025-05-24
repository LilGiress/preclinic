package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.ThemeSettingDTO;
import com.medecineWebApp.Configuration.models.setting.ThemeSetting;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ThemeSettingMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    ThemeSettingDTO themeSettingToThemeSettingDTO(ThemeSetting themeSetting);
    @InheritInverseConfiguration
    ThemeSetting themeSettingDTOToThemeSetting(ThemeSettingDTO themeSettingDTO);

}
