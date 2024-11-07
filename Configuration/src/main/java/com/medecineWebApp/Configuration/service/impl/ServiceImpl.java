package com.medecineWebApp.Configuration.service.impl;


import com.medecineWebApp.Configuration.dto.ServicesDTO;
import com.medecineWebApp.Configuration.mapper.ServiceMapper;
import com.medecineWebApp.Configuration.models.Services;
import com.medecineWebApp.Configuration.repository.service.ServiceRepository;
import com.medecineWebApp.Configuration.service.ServiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
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
            serviceToUpdate.setGroupes(service.getGroupes());
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
        Page<Services> servicePage = serviceRepository.findAll(pageable);
        return serviceMapper.serviceListToServiceDTOList(servicePage);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
