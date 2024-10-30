package com.medecineWebApp.Configuration.service;


import com.medecineWebApp.Configuration.config.jwt.JwtService;
import com.medecineWebApp.Configuration.enums.RoleType;
import com.medecineWebApp.Configuration.enums.TokenType;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.models.user.User;
import com.medecineWebApp.Configuration.notification.EmailService;
import com.medecineWebApp.Configuration.notification.EmailTemplateName;
import com.medecineWebApp.Configuration.payload.request.AuthenticationRequest;
import com.medecineWebApp.Configuration.payload.request.RegistrationOtherRequest;
import com.medecineWebApp.Configuration.payload.request.RegistrationRequest;
import com.medecineWebApp.Configuration.payload.response.AuthenticationResponse;
import com.medecineWebApp.Configuration.payload.response.ResponseMessage;
import com.medecineWebApp.Configuration.repository.role.RoleRepository;
import com.medecineWebApp.Configuration.repository.token.TokenRepository;
import com.medecineWebApp.Configuration.repository.user.UserRepository;
import com.medecineWebApp.Configuration.token.Token;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class AuthenticationService implements LogoutHandler {

    private  AuthenticationManager authenticationManager;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Value("${mailing.frontend.activation-url}")
    private String activationUrl;


    public AuthenticationService(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void  register(RegistrationRequest request) throws MessagingException {
        if (request == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

      /*  // Vérifier si l'utilisateur existe déjà
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("L'utilisateur existe déjà !");
        }

       */

        // Assigner les rôles à l'utilisateur
        Set<Roles> roles = new HashSet<>();
        for (RoleType roleType : request.getRoleTypes()) {
            Roles role = new Roles(roleType, roleType.getPermissions());
            roles.add(role);
        }

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .enabled(false)
                .accountLocked(false)
                .roles(roles)
                .build();

       userRepository.save(user);
    //  var jwtToken = jwtService.generateToken(user);
    //  var refreshToken = jwtService.generateRefreshToken(user);
      //saveUserToken(user,jwtToken);
       // userRepository.save(user);
         // sendValidationEmail(user);
    /*  return AuthenticationResponse
              .builder()
                      .accessToken(jwtToken)
                             .refreshToken(refreshToken)
                                      .build();*/

      sendValidationEmail(user);

    }



    private void sendValidationEmail(User user) throws MessagingException { // send email
        var newToken = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                (String) newToken,
                "Account activation"
        );

    }

    private Object generateAndSaveActivationToken(User user) {//generate a token
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
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
        var user = ((User) auth.getPrincipal());
        claims.put("fullName",user.getFullName());
        //var user = userRepository.findByEmail(request.getEmail()).orElse(null);

        var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal());
       // var refreshToken = jwtService.generateRefreshToken(user);
      //  revokeAllUserToken(user);
       // saveUserToken(user,jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();


    }

    private void revokeAllUserToken(User user) {
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

   // @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired . A new token has been sent to the same email address.");
        }
        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
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

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
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

    public void registerOther (RegistrationOtherRequest request) throws MessagingException {
        if (request == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

      /*  // Vérifier si l'utilisateur existe déjà
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("L'utilisateur existe déjà !");
        }

       */

        // Assigner les rôles à l'utilisateur
        Set<Roles> roles = new HashSet<>();
        for (RoleType roleType : request.getRoleTypes()) {
            Roles role = new Roles(roleType, roleType.getPermissions());
            roles.add(role);
        }

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .enabled(false)
                .accountLocked(false)
                .roles(roles)
                .build();

        userRepository.save(user);
        //  var jwtToken = jwtService.generateToken(user);
        //  var refreshToken = jwtService.generateRefreshToken(user);
        //saveUserToken(user,jwtToken);
        // userRepository.save(user);
        // sendValidationEmail(user);
    /*  return AuthenticationResponse
              .builder()
                      .accessToken(jwtToken)
                             .refreshToken(refreshToken)
                                      .build();*/

        sendValidationEmail(user);

    }

}
