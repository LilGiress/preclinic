package com.medecineWebApp.Configuration.service.impl;


import com.medecineWebApp.Configuration.dto.ServicesDTO;
import com.medecineWebApp.Configuration.mapper.ServiceMapper;
import com.medecineWebApp.Configuration.models.Departement;
import com.medecineWebApp.Configuration.models.Services;
import com.medecineWebApp.Configuration.repository.departement.DepartmentRepository;
import com.medecineWebApp.Configuration.repository.service.ServiceRepository;
import com.medecineWebApp.Configuration.service.ServiceService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final DepartmentRepository departmentRepository;

    public ServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper, DepartmentRepository departmentRepository) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ServicesDTO createService(Services service) {
        return serviceMapper.serviceToServiceDTO(serviceRepository.save(service));
    }

    @Override
    public ServicesDTO updateService(Long id, Services service) {
        Optional<Services> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            Services serviceToUpdate = serviceOptional.get();
            serviceToUpdate.setName(service.getName());

            return serviceMapper.serviceToServiceDTO(serviceRepository.save(serviceToUpdate));
        }
        throw new RuntimeException("Service with id " + id + " not found");
    }

    @Override
    public ServicesDTO getService(Long id) {
        Optional<Services> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            return serviceMapper.serviceToServiceDTO(serviceOptional.get());
        }
       throw new RuntimeException("Service with id " + id + " not found");
    }

    @Override
    public Page<ServicesDTO> getAllServices(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return serviceRepository.findAll(pageable).map(serviceMapper::serviceToServiceDTO);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public List<ServicesDTO> getAllServicesByDepartementId(Long departementId) {
        Departement department = departmentRepository.findById(departementId).orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        return serviceRepository.
                findByDepartement(department).stream().map(serviceMapper::serviceToServiceDTO).collect(Collectors.toList());
    }



}
