package com.medecineWebApp.Configuration.mapper;


import com.medecineWebApp.Configuration.dto.UserDTO;
import com.medecineWebApp.Configuration.models.user.Users;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    UserDTO UserToUserDTO(Users users);
    @InheritInverseConfiguration
    Users UserDTOToUser(UserDTO userDTO);

}
