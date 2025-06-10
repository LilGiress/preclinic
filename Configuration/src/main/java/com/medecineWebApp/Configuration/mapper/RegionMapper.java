package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.RegionDTO;
import com.medecineWebApp.Configuration.models.Region;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    RegionDTO regionToRegionDTO(Region region);
    @InheritInverseConfiguration
    Region regionDTOToRegion(RegionDTO regionDTO);
}
