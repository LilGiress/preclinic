package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.ServiceDTO;
import com.medecineWebApp.Configuration.models.Service;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);
    ServiceDTO serviceToServiceDTO(Service service);
    Service serviceDTOToService(ServiceDTO serviceDTO);
}
