package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.SalarySettingsDTO;
import com.medecineWebApp.Configuration.mapper.SalarySettingsMapper;
import com.medecineWebApp.Configuration.models.setting.SalarySettings;
import com.medecineWebApp.Configuration.repository.SalarySettingsRepository;
import com.medecineWebApp.Configuration.service.SalarySettingsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SalarySettingsServiceImpl implements SalarySettingsService {
    private final SalarySettingsRepository salarySettingsRepository;
    private final SalarySettingsMapper salarySettingsMapper;

    public SalarySettingsServiceImpl(SalarySettingsRepository salarySettingsRepository, SalarySettingsMapper salarySettingsMapper) {
        this.salarySettingsRepository = salarySettingsRepository;
        this.salarySettingsMapper = salarySettingsMapper;
    }

    @Override
    public SalarySettingsDTO createSalarySettings(SalarySettings salarySettings) {

        return salarySettingsMapper.salarySettingsDTOToSalarySettingsDTO(salarySettingsRepository.save(salarySettings));
    }

    @Override
    public SalarySettingsDTO updateSalarySettings(Long id, SalarySettings salarySettings) {
        Optional<SalarySettings> optionalSalarySettings = salarySettingsRepository.findById(id);
        if (optionalSalarySettings.isPresent()) {
            SalarySettings updatedSalarySettings = optionalSalarySettings.get();
            updatedSalarySettings.setDaPercentage(salarySettings.getDaPercentage());
            updatedSalarySettings.setHraPercentage(salarySettings.getHraPercentage());
            return salarySettingsMapper.salarySettingsDTOToSalarySettingsDTO(salarySettingsRepository.save(updatedSalarySettings));
        }
        throw new RuntimeException("SalarySettings not found");
    }

    @Override
    public Page<SalarySettingsDTO> getSalarySettings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SalarySettings> salarySettings = salarySettingsRepository.findAll(pageable);
        return  salarySettingsMapper.SalarySettingsDTOToSalarySettings(salarySettings);
    }
}
