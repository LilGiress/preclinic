package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.RolesDTO;
import com.medecineWebApp.Configuration.models.role.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface RolesMapper {
    RolesMapper INSTANCE = Mappers.getMapper(RolesMapper.class);
    RolesDTO rolesToRolesDTO(Roles roles);
    Roles rolesDTOToRoles(RolesDTO rolesDTO);
    Optional<RolesDTO> rolesDTOToRolesOptional(Optional<Roles> roles);
    Page<RolesDTO> rolesDTOPageToRolesDTOPage(Page<Roles> roles);
}
