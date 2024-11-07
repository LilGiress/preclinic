package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.ThemeSettingDTO;
import com.medecineWebApp.Configuration.mapper.ThemeSettingMapper;
import com.medecineWebApp.Configuration.models.setting.ThemeSetting;
import com.medecineWebApp.Configuration.repository.ThemeSettingRepository;
import com.medecineWebApp.Configuration.service.ThemeSettingService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ThemeSettingServiceImpl implements ThemeSettingService {
    private final ThemeSettingRepository themeSettingRepository;
    private final ThemeSettingMapper themeSettingMapper;

    public ThemeSettingServiceImpl(ThemeSettingRepository themeSettingRepository, ThemeSettingMapper themeSettingMapper) {
        this.themeSettingRepository = themeSettingRepository;
        this.themeSettingMapper = themeSettingMapper;
    }

    @Override
    public Optional<ThemeSettingDTO> getThemeByUserId(Long userId) {
        Optional<ThemeSetting> themeSetting = themeSettingRepository.findByUserId(userId);
        return themeSettingMapper.THEME_SETTING_DTO_OPTIONAL(themeSetting);
    }

    @Override
    public ThemeSettingDTO saveThemeSetting(ThemeSetting Setting) {
        ThemeSetting themeSetting = new ThemeSetting();
        themeSetting.setTheme(Setting.getTheme());
        themeSetting.setWebsiteName(Setting.getWebsiteName());
        themeSetting.setLightLogoUrl(Setting.getLightLogoUrl());
        themeSetting.setFaviconUrl(Setting.getFaviconUrl());
        themeSetting.setUserId(Setting.getUserId());
        ThemeSetting savedSetting = themeSettingRepository.save(themeSetting);
        return themeSettingMapper.themeSettingToThemeSettingDTO(savedSetting);
    }

    @Override
    public ThemeSettingDTO udpateThemeSetting(Long id,ThemeSetting themeSetting) {
        Optional<ThemeSetting> themeSettingOptional = themeSettingRepository.findById(id);
        if (themeSettingOptional.isPresent()) {
            ThemeSetting themeSetting1 = themeSettingOptional.get();
            themeSetting1.setTheme(themeSetting.getTheme());
            themeSetting1.setWebsiteName(themeSetting.getWebsiteName());
            themeSetting1.setLightLogoUrl(themeSetting.getLightLogoUrl());
            themeSetting1.setFaviconUrl(themeSetting.getFaviconUrl());
            themeSetting1.setUserId(themeSetting.getUserId());
            ThemeSetting savedSetting = themeSettingRepository.save(themeSetting1);
            return themeSettingMapper.themeSettingToThemeSettingDTO(savedSetting);
        }
        throw new ResourceNotFoundException("Theme setting with id " + id + " not found");
    }
}
