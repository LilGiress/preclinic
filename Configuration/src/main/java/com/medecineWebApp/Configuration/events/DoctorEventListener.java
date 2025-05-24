package com.medecineWebApp.Configuration.events;


import com.medecineWebApp.Configuration.models.Departement;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.models.user.Users;
import com.medecineWebApp.Configuration.payload.request.RegistrationRequest;
import com.medecineWebApp.Configuration.repository.departement.DepartmentRepository;
import com.medecineWebApp.Configuration.repository.role.RoleRepository;
import com.medecineWebApp.Configuration.repository.user.UserRepository;
import com.medecineWebApp.Configuration.service.AuthenticationService;
import jakarta.mail.MessagingException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class DoctorEventListener {
    private final UserRepository userRepository;
    private  final AuthenticationService authenticationService;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;

    public DoctorEventListener(UserRepository userRepository, AuthenticationService authenticationService, DepartmentRepository departmentRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;

        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
    }
    @KafkaListener(topics = "doctor-events",groupId = "configuration-service-group")
    public void handleDoctorEvent(DoctorEvent event) throws MessagingException {
        switch (event.getEventType()) {
            case CREATED -> {
                RegistrationRequest user = new RegistrationRequest();
                if (event.getRoles() != null) {
                    Set<Roles> roles = roleRepository.findAllByIdIn(event.getRoles());
                    user.setRoles(roles);
                }
                user.setEmail(event.getEmail());
                user.setPassword(event.getPassword());
                //user.setId(event.getUserId());
                user.setDepartments(event.getDepartments());
                user.setLastname(event.getLastname());
                user.setFirstname(event.getFirstname());
                authenticationService.register(user);
               // userRepository.save(user);
            }
            case UPDATED -> {
                Users existeUser = userRepository.findById(event.getUserId()).orElse(null);
               if (existeUser != null) {
                   existeUser.setLastname(event.getLastname());
                   existeUser.setFirstname(event.getFirstname());
                   if (event.getDepartments() != null) {
                           List<Departement> departements= departmentRepository.findAllByIdIn(event.getDepartments());
                           existeUser.setDepartments(departements);

                   }

                   existeUser.setEmail(event.getEmail());
                   if (event.getRoles() != null) {
                       Set<Roles> roles = roleRepository.findAllByIdIn(event.getRoles());
                       existeUser.setRoles(roles);
                   }
                   existeUser.setPassword(event.getPassword());
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
