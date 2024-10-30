package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.models.setting.ThemeSetting;
import com.medecineWebApp.Configuration.repository.ThemeSettingRepository;
import com.medecineWebApp.Configuration.service.ThemeSettingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThemeServiceImpl implements ThemeSettingService {

    private final ThemeSettingRepository themeSettingRepository;

    public ThemeServiceImpl(ThemeSettingRepository themeSettingRepository) {
        this.themeSettingRepository = themeSettingRepository;
    }
@Override
    public ThemeSetting saveThemeSetting(Long userId, String theme, String websiteName, String lightLogoUrl, String faviconUrl) {
        Optional<ThemeSetting> existingSetting = themeSettingRepository.findByUserId(userId);

       /* ThemeSetting themeSetting;
        if (existingSetting.isPresent()) {
            themeSetting = existingSetting.get();
            themeSetting.setTheme(theme);
            themeSetting.setWebsiteName(websiteName);
            themeSetting.setLightLogoUrl(lightLogoUrl);
            themeSetting.setFaviconUrl(faviconUrl);
        } else {
            themeSetting = new ThemeSetting(userId, theme, websiteName, lightLogoUrl, faviconUrl);
        }

        return themeSettingRepository.save(themeSetting);*/
    return null;
    }
@Override
    public Optional<ThemeSetting> getThemeByUserId(Long userId) {
        return themeSettingRepository.findByUserId(userId);
               // .orElse(new RuntimeException("")); // Default values
    }
}
