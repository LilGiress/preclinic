package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.models.setting.SalarySettings;
import com.medecineWebApp.Configuration.repository.SalarySettingsRepository;
import com.medecineWebApp.Configuration.service.SalarySettingsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SalarySettingsServiceImpl implements SalarySettingsService {
    private final SalarySettingsRepository salarySettingsRepository;

    public SalarySettingsServiceImpl(SalarySettingsRepository salarySettingsRepository) {
        this.salarySettingsRepository = salarySettingsRepository;
    }

    @Override
    public SalarySettings createSalarySettings(SalarySettings salarySettings) {
        return salarySettingsRepository.save(salarySettings);
    }

    @Override
    public SalarySettings updateSalarySettings(Long id, SalarySettings salarySettings) {
        Optional<SalarySettings> optionalSalarySettings = salarySettingsRepository.findById(id);
        if (optionalSalarySettings.isPresent()) {
            SalarySettings updatedSalarySettings = optionalSalarySettings.get();
            updatedSalarySettings.setDaPercentage(salarySettings.getDaPercentage());
            updatedSalarySettings.setHraPercentage(salarySettings.getHraPercentage());
            return salarySettingsRepository.save(updatedSalarySettings);
        }
        throw new RuntimeException("SalarySettings not found");
    }

    @Override
    public SalarySettings getSalarySettings() {
        return (SalarySettings) salarySettingsRepository.findAll();
    }
}
