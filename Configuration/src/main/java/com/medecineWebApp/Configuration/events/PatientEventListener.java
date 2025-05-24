package com.medecineWebApp.Configuration.events;


import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.models.user.Users;
import com.medecineWebApp.Configuration.payload.request.RegistrationRequest;
import com.medecineWebApp.Configuration.repository.role.RoleRepository;
import com.medecineWebApp.Configuration.repository.user.UserRepository;
import com.medecineWebApp.Configuration.service.AuthenticationService;
import jakarta.mail.MessagingException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PatientEventListener {
    private final UserRepository userRepository;
    private  final AuthenticationService authenticationService;
    private final RoleRepository roleRepository;

    public PatientEventListener(UserRepository userRepository, AuthenticationService authenticationService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;

        this.roleRepository = roleRepository;
    }
    // l'evenement kafka qui va permettre d'enregistré un nouveau utilisateur
    @KafkaListener(topics = "patient-events",groupId = "configuration-service-group")
    public void handlePatientEvent(PatientEvent event) throws MessagingException {
        switch (event.getEventType()) {
            case CREATED -> {
                RegistrationRequest user = new RegistrationRequest();
               // user.setId(event.getUserId());
                user.setEmail(event.getEmail());
                user.setPassword(event.getPassword());
                user.setLastname(event.getLastname());
                user.setFirstname(event.getFirstname());
                if (event.getRoles() != null) {
                    Set<Roles> roles = roleRepository.findAllByIdIn(event.getRoles());
                    user.setRoles(roles);
                }

                authenticationService.register(user);
                //userRepository.save(user);
            }
            case UPDATED -> {
                Users existeUser = userRepository.findById(event.getUserId()).orElse(null);
                if (existeUser != null) {
                    existeUser.setEmail(event.getEmail());
                    existeUser.setPassword(event.getPassword());
                    existeUser.setLastname(event.getLastname());
                    existeUser.setFirstname(event.getFirstname());
                    if (event.getRoles() != null) {
                        Set<Roles> roles = roleRepository.findAllByIdIn(event.getRoles());
                        existeUser.setRoles(roles);
                    }

                    userRepository.save(existeUser);
                }
            }
            case DELETED -> {
                Users user = userRepository.findById(event.getUserId()).orElse(null);
                if (user != null) {
                    userRepository.delete(user);
                }
            }
        }
    }
}
