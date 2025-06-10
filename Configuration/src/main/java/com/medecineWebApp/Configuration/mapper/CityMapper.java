package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.CityDTO;
import com.medecineWebApp.Configuration.models.City;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityDTO cityToCityDTO(City city);
    @InheritInverseConfiguration
    City cityDTOToCity(CityDTO cityDTO);
}
