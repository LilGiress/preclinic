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
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    ServicesDTO serviceToServiceDTO(Services service);
    @InheritInverseConfiguration
    Services serviceDTOToService(ServicesDTO servicesDTO);

}
