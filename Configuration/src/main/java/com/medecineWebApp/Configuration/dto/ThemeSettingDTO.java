package com.medecineWebApp.Configuration.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ThemeSettingDTO {
    private Long id;

    private Long userId; // ID of the user

    private String theme; // e.g., "LIGHT", "DARK", "CUSTOM"

    private String websiteName;  // Name of the website (customizable)

    private String lightLogoUrl; // URL for the light theme logo

    private String faviconUrl;
}
