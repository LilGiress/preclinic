package com.medecineWebApp.Configuration.mapper;


import com.medecineWebApp.Configuration.dto.UserDTO;
import com.medecineWebApp.Configuration.models.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO UserToUserDTO(User user);
    User UserDTOToUser(UserDTO userDTO);

}
