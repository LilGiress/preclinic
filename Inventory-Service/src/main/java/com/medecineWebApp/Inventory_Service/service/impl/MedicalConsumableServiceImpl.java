package com.medecineWebApp.Inventory_Service.service.impl;

import com.medecineWebApp.Inventory_Service.dto.MedicalConsumableDTO;
import com.medecineWebApp.Inventory_Service.mapper.MedicalConsumableMapper;
import com.medecineWebApp.Inventory_Service.models.MedicalConsumable;
import com.medecineWebApp.Inventory_Service.repository.MedicalConsumableRepository;
import com.medecineWebApp.Inventory_Service.service.MedicalConsumableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalConsumableServiceImpl implements MedicalConsumableService {
    private final MedicalConsumableMapper medicalConsumableMapper;
    private final MedicalConsumableRepository medicalConsumableRepository;

    public MedicalConsumableServiceImpl(MedicalConsumableMapper medicalConsumableMapper, MedicalConsumableRepository medicalConsumableRepository) {
        this.medicalConsumableMapper = medicalConsumableMapper;
        this.medicalConsumableRepository = medicalConsumableRepository;
    }

    @Override
    public List<MedicalConsumableDTO> getAllConsumables() {
        List<MedicalConsumable> allConsumables = medicalConsumableRepository.findAll();
        return allConsumables.stream()
                .map(medicalConsumableMapper::toMedicalConsumableDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalConsumableDTO addConsumable(MedicalConsumable consumable) {
        return medicalConsumableMapper.toMedicalConsumableDTO(medicalConsumableRepository.save(consumable));
    }
}
