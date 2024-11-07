package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.ThemeSettingDTO;
import com.medecineWebApp.Configuration.models.setting.ThemeSetting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ThemeSettingMapper {
    ThemeSettingMapper INSTANCE = Mappers.getMapper(ThemeSettingMapper.class);
    ThemeSettingDTO themeSettingToThemeSettingDTO(ThemeSetting themeSetting);
    ThemeSetting themeSettingDTOToThemeSetting(ThemeSettingDTO themeSettingDTO);
    Optional<ThemeSettingDTO> THEME_SETTING_DTO_OPTIONAL(Optional<ThemeSetting> themeSetting);
}
