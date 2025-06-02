package com.medecineWebApp.Configuration.service;


import com.medecineWebApp.Configuration.config.jwt.JwtService;
import com.medecineWebApp.Configuration.dto.ThemeSettingDTO;
import com.medecineWebApp.Configuration.dto.UserDTO;
import com.medecineWebApp.Configuration.enums.TokenType;
import com.medecineWebApp.Configuration.mapper.DepartementMapper;
import com.medecineWebApp.Configuration.mapper.UserMapper;
import com.medecineWebApp.Configuration.models.Departement;
import com.medecineWebApp.Configuration.models.kafka.UserEvent;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.models.user.Users;
import com.medecineWebApp.Configuration.payload.request.AuthenticationRequest;
import com.medecineWebApp.Configuration.payload.request.RegistrationRequest;
import com.medecineWebApp.Configuration.payload.response.AuthenticationResponse;
import com.medecineWebApp.Configuration.repository.ThemeSettingRepository;
import com.medecineWebApp.Configuration.repository.departement.DepartmentRepository;
import com.medecineWebApp.Configuration.repository.role.RoleRepository;
import com.medecineWebApp.Configuration.repository.token.TokenRepository;
import com.medecineWebApp.Configuration.repository.user.UserRepository;
import com.medecineWebApp.Configuration.service.kafka.EmailService;
import com.medecineWebApp.Configuration.service.kafka.EmailTemplateName;
import com.medecineWebApp.Configuration.service.kafka.NotificationService;
import com.medecineWebApp.Configuration.token.Token;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class AuthenticationService implements LogoutHandler {

    private final  AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private  final NotificationService notificationService;

    private final JwtService jwtService;

    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final DepartementMapper departementMapper;
    private final EmailService emailService;
    private final ThemeSettingService themeSettingService ;
    private final UserService userService;
    private final UserMapper userMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Value("${mailing.frontend.activation-url}")
    private String activationUrl;


    public AuthenticationService(@Lazy AuthenticationManager authenticationManager, UserRepository userRepository, TokenRepository tokenRepository, NotificationService notificationService, JwtService jwtService, RoleRepository roleRepository, DepartmentRepository departmentRepository, DepartementMapper departementMapper, EmailService emailService, ThemeSettingRepository themeSettingRepository, ThemeSettingService themeSettingService, UserService userService, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.notificationService = notificationService;
        this.jwtService = jwtService;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.departementMapper = departementMapper;
        this.emailService = emailService;
        this.themeSettingService = themeSettingService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public UserDTO register(RegistrationRequest request) throws MessagingException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }

        // Vérifier si l'utilisateur existe déjà
        Users existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("L'utilisateur existe déjà !");
        }
        log.info("Roles récupérés: {}", request.getRoles());
        // Récupérer les rôles à partir des IDs
        List<Long> roleIds = request.getRoles().stream()
                .map(Roles::getId)
                .toList();

      //  Set<Roles> roles = new HashSet<>(roleRepository.findAllByIdIn(roleIds));
        Set<Roles> roles = new HashSet<>(roleRepository.findAllByIdIn(roleIds));
        if (roles.isEmpty()) {
            throw new IllegalArgumentException("Aucun rôle valide n'a été trouvé.");
        }


        // Récupérer les départements
        //List<Long> departmentIds =  request.getDepartments(); // Si déjà List<Long>
        List<Long> departmentIds = Optional.ofNullable(request.getDepartments())
                .orElse(Collections.emptyList());

        List<Departement> departements = departmentRepository.findAllByIdIn(departmentIds);

        // Générer un mot de passe aléatoire
        String generatedPassword = generateRandomPassword(10);

        // Création de l'utilisateur
        var user = Users.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .departments(departements)
                .enabled(false)
                .accountLocked(false)
                .roles(roles)
                .build();

        // Sauvegarde en base de données
            log.warn("request +++++ récupérés: {}", request);
        user = userRepository.save(user);

        sendValidationEmail(user);
        return userMapper.UserToUserDTO(userRepository.save(user))  ;
    }

    private void sendValidationEmail(Users user) throws MessagingException { // send email
        var newToken = generateAndSaveActivationToken(user);
        // Générer un jeton d'activation unique
      //  String activationToken = UUID.randomUUID().toString();
        Token tokenExiste= tokenRepository.findByUsers(user);
        String activationToken = tokenExiste.getToken();
        // Génération du lien d'activation
        String activationLink = activationUrl + "?token=" + activationToken;

        StringBuilder emailContent = new StringBuilder();
        emailContent.append("    <p>Bonjour <strong>").append(user.getFullName()).append("</strong>,</p>\n");
        emailContent.append("    <p>Bienvenue dans notre système de gestion de clinique. Votre compte a été créé avec succès en tant que patient.</p>\n");
        emailContent.append("    <p>Pour activer votre compte et commencer à prendre des rendez-vous, consulter vos dossiers médicaux et gérer vos informations personnelles, veuillez cliquer sur le lien ci-dessous :</p>\n");
        emailContent.append("    <p><a href=\"").append(activationLink).append("\" style=\"color: #4CAF50; font-weight: bold;\">Activer mon compte</a></p>\n");
        emailContent.append("    <p>Votre code d'activation du  compte.</p>\n");
        emailContent.append("    <p>\"").append(activationToken).append("</p>\n");

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationLink,
                //(String) newToken,
                activationToken,
                "Activation de votre compte clinique",
                emailContent.toString(),
                getClinicLogo()
        );
        // Envoyer l'email de bienvenue avec les identifiants
        UserEvent event = new UserEvent();
        event.setPassword(user.getPassword());
        event.setEmail(user.getEmail());
        event.setUsername(user.getFullName());

        notificationService.sendWelcomeNotification(event);

    }
    //generate a token
    private Object generateAndSaveActivationToken(Users user) {
        Token tokenExiste= tokenRepository.findByUsers(user);
        if (tokenExiste == null) {
            String generatedToken = generateActivationCode(6);
            var token = Token.builder()
                    .token(generatedToken)
                    .tokenType(TokenType.BEARER)
                    .createdAt(LocalDateTime.now())
                    .expiresAt(LocalDateTime.now().plusHours(24))
                    .users(user)
                    .build();
            tokenRepository.save(token);
            return generatedToken;
        }else {
            String generatedToken = generateActivationCode(6);
            var token = Token.builder()
                    .id(tokenExiste.getId())
                    .token(generatedToken)
                    .tokenType(TokenType.BEARER)
                    .createdAt(LocalDateTime.now())
                    .expiresAt(LocalDateTime.now().plusHours(24))
                    .users(tokenExiste.getUsers())
                    .build();
            tokenRepository.save(token);
            return generatedToken;
        }

    }

    private String generateActivationCode(int length) {//
        String characters= "0123456789";
        StringBuilder result = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int numberIndex = random.nextInt(characters.length());
            result.append(characters.charAt(numberIndex));
        }
        return result.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        Authentication auth=   authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var claims = new HashMap<String, Object>();
        var user = ((Users) auth.getPrincipal());
        claims.put("fullName",user.getFullName());
        //var user = userRepository.findByEmail(request.getEmail()).orElse(null);

        var jwtToken = jwtService.generateToken(claims, (Users) auth.getPrincipal());
       // var refreshToken = jwtService.generateRefreshToken(user);
      //  revokeAllUserToken(user);
       // saveUserToken(user,jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();


    }

    private void revokeAllUserToken(Users user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty()) {
    return;
}
    validUserTokens.forEach(token -> {
    token.setIsexpired(true);
    token.setRevoked(true);
    });
        tokenRepository.saveAll(validUserTokens);
    }

    @Transactional
    public void activateAccount(String token) throws MessagingException {

        log.info("token récupérés: {}", token);
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUsers());
            throw new RuntimeException("Activation token has expired . A new token has been sent to the same email address.");
        }
        var user = userRepository.findById(savedToken.getUsers().getId())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now().plusHours(24));
        tokenRepository.save(savedToken);


    }

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        var storedToken = tokenRepository.findByToken(jwt)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (storedToken != null ) {
            storedToken.setIsexpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
        }

    }

    private void saveUserToken(Users user, String jwtToken) {
        var token = Token.builder()
                .users(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .isexpired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }


    public boolean existsByFirstnameOrLastname(String firstname,String lastname) {
      return userRepository.existsByFirstnameOrLastname(firstname,lastname);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }



    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    // recuperation du logo de la clinic
    public String getClinicLogo() {
        Optional<ThemeSettingDTO> themeSetting = themeSettingService.getTheme();
      // log.info("Utilisateur connecter",authentication.getName());
       // if (themeSetting.isPresent()) {
            // Vérifier si la configuration du thème existe et récupérer le logo
            return themeSetting.map(ThemeSettingDTO::getLightLogoUrl)
                    .orElse(getDefaultClinicLogoBase64()); // Remplace par un logo par défaut si absent
       // }

        //return getDefaultClinicLogoBase64(); // Si l'utilisateur n'est pas authentifié, retourne un logo par défaut
    }

    public String getDefaultClinicLogoBase64() {
        try {
            ClassPathResource classPathResource = new ClassPathResource("static/images/logo-dark.png");
            byte[]bytes= Files.readAllBytes(classPathResource.getFile().toPath());
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
