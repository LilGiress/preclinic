package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.CountryDTO;
import com.medecineWebApp.Configuration.models.Country;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDTO countryToCountryDTO(Country country);
    @InheritInverseConfiguration
    Country countryDTOToCountry(CountryDTO countryDTO);
}
