package com.medecineWebApp.Configuration.mapper;


import com.medecineWebApp.Configuration.dto.UserDTO;
import com.medecineWebApp.Configuration.models.user.User;
import com.medecineWebApp.Configuration.payload.response.UserResponse;

public class UserMapper {
    // Convert User JPA Entity into UserDto
    public static UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getFirstname(),
                user.getFullName(),
                user.getEmail(),
                user.getPassword()
        );
        return userDTO;
    }
    // Convert UserDto into User JPA Entity
    public static User mapToUser(UserDTO userDTO) {
        User user = new User(
               /* userDTO.getId(),
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.getEmail(),
                userDTO.getPassword()*/
        );
        return user;
    }

    public static UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .enabled(user.isEnabled())
                .accountLocked(user.isAccountLocked())
                .roles(user.getRoles())
                .build();
    }

    public static User maptoResponse(UserResponse userResponse) {
        return User.builder()
                .id(userResponse.getId())
                .firstname(userResponse.getFirstname())
                .lastname(userResponse.getLastname())
                .email(userResponse.getEmail())
                .password(userResponse.getPassword())
                .enabled(userResponse.isEnabled())
                .accountLocked(userResponse.isAccountLocked())
                .roles(userResponse.getRoles())
                .build();
    }
}
