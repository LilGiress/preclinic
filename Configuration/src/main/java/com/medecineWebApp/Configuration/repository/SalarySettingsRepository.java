package com.medecineWebApp.Configuration.repository;

import com.medecineWebApp.Configuration.models.setting.SalarySettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalarySettingsRepository extends JpaRepository<SalarySettings, Long> {
}
