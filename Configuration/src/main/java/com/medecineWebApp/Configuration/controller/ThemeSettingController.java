package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.ThemeSettingDTO;
import com.medecineWebApp.Configuration.models.setting.ThemeSetting;
import com.medecineWebApp.Configuration.service.ThemeSettingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/theme")
public class ThemeSettingController {

    private final ThemeSettingService themeSettingService;

    public ThemeSettingController( ThemeSettingService themeSettingService) {
        this.themeSettingService = themeSettingService;
    }


    // Set the theme and other settings for a user
    @PostMapping("/set")
    public ThemeSettingDTO setTheme(
            @RequestBody ThemeSetting themeSetting
            ) {
        return themeSettingService.saveThemeSetting(themeSetting);
    }

    // Get the theme settings for a user
    @GetMapping("/get")
    public Optional<ThemeSettingDTO> getTheme(@RequestParam Long userId) {
        return themeSettingService.getThemeByUserId(userId);
    }
    @PutMapping("/update")
    public ThemeSettingDTO updateThemeSetting(
            @RequestParam Long id,
            @RequestBody ThemeSetting themeSetting) {
        return themeSettingService.udpateThemeSetting(id,themeSetting);

    }
}
