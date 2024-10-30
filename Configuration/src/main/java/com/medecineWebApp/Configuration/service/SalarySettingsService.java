package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.models.setting.SalarySettings;

import java.util.Optional;

public interface SalarySettingsService {
    SalarySettings createSalarySettings(SalarySettings salarySettings);
    SalarySettings updateSalarySettings(Long id, SalarySettings salarySettings);
    SalarySettings getSalarySettings();

}
