package com.medecineWebApp.Configuration.service.userServiceImpl;


import com.medecineWebApp.Configuration.config.jwt.JwtService;
import com.medecineWebApp.Configuration.mapper.UserMapper;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.models.user.User;
import com.medecineWebApp.Configuration.notification.EmailService;
import com.medecineWebApp.Configuration.payload.request.ChangePasswordRequest;
import com.medecineWebApp.Configuration.payload.request.RegistrationOtherRequest;
import com.medecineWebApp.Configuration.payload.request.RegistrationRequest;
import com.medecineWebApp.Configuration.payload.response.PageResponse;
import com.medecineWebApp.Configuration.payload.response.ResponseMessage;
import com.medecineWebApp.Configuration.payload.response.UserResponse;
import com.medecineWebApp.Configuration.repository.role.RoleRepository;
import com.medecineWebApp.Configuration.repository.user.UserRepository;
import com.medecineWebApp.Configuration.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class UserServiceImpl  implements UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void changePassword(ChangePasswordRequest request, Principal userConnected) {

        var user = (User)((UsernamePasswordAuthenticationToken) userConnected).getPrincipal();

        // check if the current password is correct
        //!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())
        if (!new BCryptPasswordEncoder().matches(request.getCurrentPassword(), user.getPassword())) {
           throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalCallerException("Passwords do not match");
        }
        // mise à jour du mot de pass

        user.setPassword(new BCryptPasswordEncoder().encode(request.getNewPassword()));

        userRepository.save(user);

    }

    @Override
    public Optional<User> forgotPassword(String email) {
        if (userRepository.findByEmail(email).isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        return userRepository.findByEmail(email);

    }

    @Override
    public void updatepassword(ChangePasswordRequest request) {
        if (request==null && request.getUserId()<0){
            throw new RuntimeException("request and user is null and Cannot change password");
        }

       User user = userRepository.findById(request.getUserId()).orElseThrow(RuntimeException::new);


        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalCallerException("Passwords do not match");
        }
        // mise à jour du mot de pass

        user.setPassword(new BCryptPasswordEncoder().encode(request.getNewPassword()));

        userRepository.save(user);

    }

    @Override
    public ResponseMessage<User> getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new ResponseMessage<>(200, "successfull", user);
        } else {
            return new ResponseMessage<>(404, "User not found", null);
        }
    }

    @Override
    public PageResponse<UserResponse> getAllUsers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size,Sort.by("createdDate").descending());
        Page<User> userPage = userRepository.findAll(pageRequest);
        List<UserResponse> userresponse = userPage.stream()
                .map(UserMapper::mapToUserResponse)
                .toList();
        return  new PageResponse<>(
                userresponse,
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.isFirst(),
                userPage.isLast()

        );

    }

    @Override
    public void deleteUserById(Long userId) {
        if (userId >0) {
            if (userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
            } else {
                throw new EntityNotFoundException("User not found with id: " + userId);
            }
        } else {
            throw new IllegalArgumentException("User id cannot be null");
        }
    }

    @Override
    public ResponseMessage<User> updateUser(Long userId, User user ) {
        User olduser = userRepository.findById(userId).orElseThrow(
                ()-> new EntityNotFoundException("User not found with id: " + userId)
        );

        olduser.setEmail(user.getEmail());
        olduser.setPassword(user.getPassword());
        olduser.setFirstname(user.getFirstname());
        olduser.setLastname(user.getLastname());
        olduser.setRoles(user.getRoles());
        olduser.setAccountLocked(user.isAccountLocked());
        olduser = userRepository.save(olduser);
        return new ResponseMessage<>(200, "successfull", olduser);
    }

    @Override
    public Optional<User> getCurrentUser(String token) {
        String username = jwtService.extractUsername(token);

        return userRepository.findByEmail(username);
    }






}
