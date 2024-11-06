package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.DepartementDTO;
import com.medecineWebApp.Configuration.models.Departement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartementMapper {
    DepartementMapper INSTANCE = Mappers.getMapper(DepartementMapper.class);
    DepartementDTO departementToDepartementDTO(Departement departement);
    Departement departementDTOToDepartement(DepartementDTO departementDTO);
}
