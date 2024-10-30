package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.models.setting.ThemeSetting;
import com.medecineWebApp.Configuration.service.ThemeSettingService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/theme")
public class ThemeSettingController {

    private final ThemeSettingService themeSettingService;

    public ThemeSettingController(ThemeSettingService themeSettingService) {
        this.themeSettingService = themeSettingService;
    }

    // Set the theme and other settings for a user
    @PostMapping("/set")
    public ThemeSetting setTheme(
            @RequestParam Long userId,
            @RequestParam String theme,
            @RequestParam String websiteName,
            @RequestParam String lightLogoUrl,
            @RequestParam String faviconUrl) {
        return themeSettingService.saveThemeSetting(userId, theme, websiteName, lightLogoUrl, faviconUrl);
    }

    // Get the theme settings for a user
    @GetMapping("/get")
    public Optional<ThemeSetting> getTheme(@RequestParam Long userId) {
        return themeSettingService.getThemeByUserId(userId);
    }
}
