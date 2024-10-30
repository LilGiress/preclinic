package com.medecineWebApp.Configuration.repository;

import com.medecineWebApp.Configuration.models.setting.ThemeSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeSettingRepository extends JpaRepository<ThemeSetting, Long> {
    Optional<ThemeSetting> findByUserId(Long userId);

}
