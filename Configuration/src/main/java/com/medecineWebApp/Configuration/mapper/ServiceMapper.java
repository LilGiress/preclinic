package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.ServicesDTO;
import com.medecineWebApp.Configuration.models.Services;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServicesDTO serviceToServiceDTO(Services service);
    @InheritInverseConfiguration
    Services serviceDTOToService(ServicesDTO servicesDTO);

}
