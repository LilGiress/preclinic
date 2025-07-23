package com.medecineWebApp.Configuration.service.userServiceImpl;


import com.medecineWebApp.Configuration.config.jwt.JwtService;
import com.medecineWebApp.Configuration.dto.UserDTO;
import com.medecineWebApp.Configuration.exception.BusinessErrorCodes;
import com.medecineWebApp.Configuration.exception.CustomAppException;
import com.medecineWebApp.Configuration.exception.UserNotFoundException;
import com.medecineWebApp.Configuration.mapper.UserMapper;
import com.medecineWebApp.Configuration.models.kafka.PasswordResetEvent;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.models.user.PasswordResetToken;
import com.medecineWebApp.Configuration.models.user.Users;
import com.medecineWebApp.Configuration.payload.request.ChangePasswordRequest;
import com.medecineWebApp.Configuration.payload.request.ResetPasswordRequest;
import com.medecineWebApp.Configuration.payload.response.ResponseMessage;
import com.medecineWebApp.Configuration.repository.role.RoleRepository;
import com.medecineWebApp.Configuration.repository.user.PasswordResetTokenRepository;
import com.medecineWebApp.Configuration.repository.user.UserRepository;
import com.medecineWebApp.Configuration.service.UserService;
import com.medecineWebApp.Configuration.service.kafka.NotificationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;


    private final NotificationService notificationService;
    private final RoleRepository roleRepository;
    private  final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, JwtService jwtService, NotificationService notificationService, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.notificationService = notificationService;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }



    @Override
    public UserDTO forgotPassword(String email) {
        if (userRepository.findByEmail(email) == null) {
            throw new CustomAppException(BusinessErrorCodes.ACCOUNT_NOT_FOUND);
        }
        return userMapper.UserToUserDTO(userRepository.findByEmail(email));

    }



    @Override
    public void resetPassword(ResetPasswordRequest request) {
        if(request==null){
            throw new IllegalArgumentException("request is null");
        }

        Users user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new CustomAppException(BusinessErrorCodes.ACCOUNT_NOT_FOUND);
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new CustomAppException(BusinessErrorCodes.NEW_PASSWORD_DOES_NOT_MATCH);
        }
            user.setPassword(new BCryptPasswordEncoder().encode(request.getNewPassword()));

        userRepository.save(user);

    }


    @Override
    public ResponseMessage<UserDTO> getUserById(Long userId) {
        Optional<Users> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            return new ResponseMessage<>(200, "successfull", userMapper.UserToUserDTO(user));
        } else {
            return new ResponseMessage<>(404, "User not found", null);
        }
    }

    @Override
    public Page<UserDTO> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
       // PageRequest pageRequest = PageRequest.of(page, size,Sort.by("createdDate").descending());
        Page<Users> userPage = userRepository.findAll(pageable);

        return (Page<UserDTO>) userMapper.UserDTOToUser((UserDTO) userPage);

    }

    @Override
    public void deleteUserById(Long userId) {
        if (userId >0) {
            Users user = userRepository.findById(userId).orElseThrow(
                    ()-> new CustomAppException(BusinessErrorCodes.ACCOUNT_NOT_FOUND)
            );
            if (user!= null) {
                user.setEnabled(false);
                userRepository.save(user);
            } else {
                throw new CustomAppException(BusinessErrorCodes.ACCOUNT_NOT_FOUND);
            }
        } else {
            throw new UserNotFoundException("User id cannot be null");
        }
    }

    @Override
    public ResponseMessage<UserDTO> updateUser(Long userId, Users user ) {
        Users olduser = userRepository.findById(userId).orElseThrow(
                ()-> new CustomAppException(BusinessErrorCodes.ACCOUNT_NOT_FOUND)
        );

        olduser.setEmail(user.getEmail());
        olduser.setPassword(user.getPassword());
        olduser.setFirstname(user.getFirstname());
        olduser.setUsername(user.getUsername());
        olduser.setLastname(user.getLastname());
        olduser.setRoles(user.getRoles());
        olduser.setAccountLocked(user.isAccountLocked());
        olduser = userRepository.save(olduser);
        return new ResponseMessage<>(200, "successfull", userMapper.UserToUserDTO(olduser));
    }

    @Override
    public UserDTO getCurrentUser(String token) {
        token = token.replace("Bearer ", ""); // Nettoie le token
        String username = jwtService.extractUsername(token);
        Users user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"+ username));

        // Vérification de validité du token avec UserDetails
        if (!jwtService.isTokenValid(token, user)) {
            throw new IllegalArgumentException("Invalid or expired token");
        }
        return userMapper.UserToUserDTO(user);
    }



    @Override
    public List<UserDTO> getUsersByRole(List<String> roles) {
        Set<Roles> roleSet = new HashSet<>(roleRepository.findByNameIn(roles));
        return userRepository.findUsersByRoles(roleSet)
                .stream()
                .map(userMapper::UserToUserDTO)
                .collect(Collectors.toList());
    }

    // Générer un code de vérification à 6 chiffres
    private String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000); // Générer un nombre entre 100000 et 999999
        return String.valueOf(code);
    }

    public Long getUserIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(Users::getId) // Supposons que ta classe User a un getId()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
