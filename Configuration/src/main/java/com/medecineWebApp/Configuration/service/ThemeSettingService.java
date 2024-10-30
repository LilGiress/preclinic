package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.models.setting.ThemeSetting;

import java.util.Optional;


public interface ThemeSettingService {
    Optional<ThemeSetting> getThemeByUserId(Long userId);
    ThemeSetting saveThemeSetting(Long userId, String theme, String websiteName, String lightLogoUrl, String faviconUrl);
}
