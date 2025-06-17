package com.medecineWebApp.Configuration.service.userServiceImpl;


import com.medecineWebApp.Configuration.config.jwt.JwtService;
import com.medecineWebApp.Configuration.dto.UserDTO;
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
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    //private final EmailService emailService;
    private final NotificationService notificationService;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private  final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, JwtService jwtService,  PasswordResetTokenRepository passwordResetTokenRepository, NotificationService notificationService, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.notificationService = notificationService;
        // this.emailService = emailService;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void changePassword(ChangePasswordRequest request, Principal userConnected) {

        var user = (Users)((UsernamePasswordAuthenticationToken) userConnected).getPrincipal();

        // check if the current password is correct
        //!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())
        if (!new BCryptPasswordEncoder().matches(request.getCurrentPassword(), user.getPassword())) {
           throw new UserNotFoundException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new UserNotFoundException("Passwords do not match");
        }
        // mise à jour du mot de pass

        user.setPassword(new BCryptPasswordEncoder().encode(request.getNewPassword()));

        userRepository.save(user);

    }

    @Override
    public UserDTO forgotPassword(String email) {
        if (userRepository.findByEmail(email) == null) {
            throw new UserNotFoundException("User not found");
        }
        return userMapper.UserToUserDTO(userRepository.findByEmail(email));

    }

    @Override
    public void updatepassword(ChangePasswordRequest request) {
        if (request==null && request.getUserId()<0){
            throw new RuntimeException("request and user is null and Cannot change password");
        }

       Users user = userRepository.findById(request.getUserId()).orElseThrow(RuntimeException::new);


        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new UserNotFoundException("Passwords do not match");
        }
        // mise à jour du mot de pass

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
                    ()-> new UserNotFoundException("User not found with id: " + userId)
            );
            if (user!= null) {
                user.setEnabled(false);
                userRepository.save(user);
            } else {
                throw new UserNotFoundException("User not found with id: " + userId);
            }
        } else {
            throw new UserNotFoundException("User id cannot be null");
        }
    }

    @Override
    public ResponseMessage<UserDTO> updateUser(Long userId, Users user ) {
        Users olduser = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("User not found with id: " + userId)
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
    public String requestPasswordReset(String email) {
        // Vérifier si l'utilisateur existe
        if (!userRepository.existsByEmail(email)) {
            throw new UserNotFoundException("Utilisateur introuvable !");
        }

        // Générer un code de vérification à 6 chiffres
        String verificationCode = generateVerificationCode();

        // Supprimer tout ancien code pour cet email
        passwordResetTokenRepository.deleteByEmail(email);

        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(email);
        token.setExpirationTime(LocalDateTime.now().plusMinutes(10));
        token.setVerificationCode(verificationCode);
        passwordResetTokenRepository.save(token);
        PasswordResetEvent eventReset = new PasswordResetEvent();
        eventReset.setEmail(email);
        eventReset.setVerificationCode(verificationCode);
        notificationService.sendPasswordResetNotification(eventReset);
    return "Code de vérification envoyé à " + email;
    }

    @Override
    public String resetPassword(ResetPasswordRequest request) {
        if(request==null){
            throw new IllegalArgumentException("request is null");
        }
      /*  PasswordResetToken tokenReset = passwordResetTokenRepository.findByEmailAndVerificationCode(request.getEmail(), request.getVerificationCode())
                .orElseThrow(() -> new IllegalArgumentException("Code de vérification invalide ou expiré"));

        // Vérifier si le code est expiré
        if(tokenReset.getExpirationTime().isBefore(LocalDateTime.now())){
            throw new IllegalCallerException("Code expiré !");

        }*/

        Users user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + request.getEmail());
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new UserNotFoundException("Passwords do not match");
        }else {
            user.setPassword(new BCryptPasswordEncoder().encode(request.getNewPassword()));
        }
        userRepository.save(user);
        passwordResetTokenRepository.deleteByEmail(request.getEmail());
           return "Mot de passe réinitialisé avec succès !";
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
