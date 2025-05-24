package com.medecineWebApp.Asset.Management.service.impl;

import com.medecineWebApp.Asset.Management.dto.MaintenanceDTO;
import com.medecineWebApp.Asset.Management.exception.MaintenanceNotFoundException;
import com.medecineWebApp.Asset.Management.mapper.MaintenanceMapper;
import com.medecineWebApp.Asset.Management.models.Maintenance;
import com.medecineWebApp.Asset.Management.repository.MaintenanceRepository;
import com.medecineWebApp.Asset.Management.service.MaintenanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private  MaintenanceMapper maintenanceMapper;


    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository ) {
        this.maintenanceRepository = maintenanceRepository;

    }

    @Override
    public List<MaintenanceDTO> getAllMaintenances() {
        return maintenanceRepository.findAll().stream()
                .map(maintenanceMapper::toMaintenanceDTO)
                .toList();
    }

    @Override
    public MaintenanceDTO getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id)
                .map(maintenanceMapper::toMaintenanceDTO)
                .orElseThrow(() -> new MaintenanceNotFoundException("Maintenance not found"));
    }

    @Override
    public MaintenanceDTO createMaintenance(Maintenance maintenance) {
        return maintenanceMapper.toMaintenanceDTO(maintenanceRepository.save(maintenance));
    }

    @Override
    public void deleteMaintenance(Long id) {
    maintenanceRepository.deleteById(id);
    }
}
