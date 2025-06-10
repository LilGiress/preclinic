package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.DepartementDTO;
import com.medecineWebApp.Configuration.models.Departement;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DepartementMapper {
    DepartementDTO departementToDepartementDTO(Departement departement);
    @InheritInverseConfiguration
    Departement departementDTOToDepartement(DepartementDTO departementDTO);
}
