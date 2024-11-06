package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.CountryDTO;
import com.medecineWebApp.Configuration.models.user.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);
    CountryDTO countryToCountryDTO(Country country);
    Country countryDTOToCountry(CountryDTO countryDTO);
}
