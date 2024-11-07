package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.ServicesDTO;
import com.medecineWebApp.Configuration.models.Services;
import org.springframework.data.domain.Page;

public interface ServiceService {
    ServicesDTO createService(Services service);
    ServicesDTO updateService(Long id, Services service);
    ServicesDTO getService(Long id);
    Page<ServicesDTO> getAllServices(int page, int size);
    void deleteService(Long id);
}
