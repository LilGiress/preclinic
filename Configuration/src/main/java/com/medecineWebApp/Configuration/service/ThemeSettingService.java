package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.ThemeSettingDTO;
import com.medecineWebApp.Configuration.models.setting.ThemeSetting;

import java.util.Optional;


public interface ThemeSettingService {
    Optional<ThemeSettingDTO> getThemeByUserId(Long userId);
    ThemeSettingDTO saveThemeSetting(ThemeSetting themeSetting);
    ThemeSettingDTO udpateThemeSetting(Long id, ThemeSetting themeSetting);
}
