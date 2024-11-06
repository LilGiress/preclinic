package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.RolesDTO;
import com.medecineWebApp.Configuration.models.role.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RolesMapper {
    RolesMapper INSTANCE = Mappers.getMapper(RolesMapper.class);
    RolesDTO rolesToRolesDTO(Roles roles);
    Roles rolesDTOToRoles(RolesDTO rolesDTO);
}
