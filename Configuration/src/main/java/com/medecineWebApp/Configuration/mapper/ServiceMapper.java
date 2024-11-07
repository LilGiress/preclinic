package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.ServicesDTO;
import com.medecineWebApp.Configuration.models.Services;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);
    ServicesDTO serviceToServiceDTO(Services service);
    Services serviceDTOToService(ServicesDTO servicesDTO);
    Page<ServicesDTO> serviceListToServiceDTOList(Page<Services> services);
}
