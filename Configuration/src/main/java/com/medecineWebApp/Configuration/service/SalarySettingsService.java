package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.SalarySettingsDTO;
import com.medecineWebApp.Configuration.models.setting.SalarySettings;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface SalarySettingsService {
    SalarySettingsDTO createSalarySettings(SalarySettings salarySettings);
    SalarySettingsDTO updateSalarySettings(Long id, SalarySettings salarySettings);
    Page<SalarySettingsDTO> getSalarySettings(int page, int size);

}
