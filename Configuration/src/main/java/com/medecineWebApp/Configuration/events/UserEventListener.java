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

import java.util.List;
import java.util.Set;

@Service
public class UserEventListener {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;

    public UserEventListener(UserRepository userRepository, AuthenticationService authenticationService, RoleRepository roleRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
    }
@KafkaListener(topics = "employee-events",groupId = "configuration-service-group")
    public void handleEmployeeEvent(EmployeeEvent event) throws MessagingException {
        switch (event.getEventType()) {
            case CREATED -> {
                RegistrationRequest user = new RegistrationRequest();
                if (event.getRoles() != null) {
                    Set<Roles> roles = roleRepository.findAllByIdIn(event.getRoles());
                    user.setRoles(roles);
                }
              //  user.setId(event.getUserId());
                user.setDepartments(event.getDepartments());
                user.setFirstname(event.getFirstname());
                user.setLastname(event.getLastname());
                user.setEmail(event.getEmail());
                user.setPassword(event.getPassword());
                authenticationService.register(user);
              //  userRepository.save(user);

            }
            case UPDATED -> {

                Users existingUser = userRepository.findById(event.getUserId()).orElse(null);
                if (existingUser != null) {
                    if (event.getRoles() != null) {
                        Set<Roles> roles = roleRepository.findAllByIdIn(event.getRoles());
                        existingUser.setRoles(roles);
                    }
                    existingUser.setFirstname(event.getFirstname());
                    existingUser.setLastname(event.getLastname());
                    existingUser.setEmail(event.getEmail());
                    existingUser.setPassword(event.getPassword());
                    if (event.getDepartments() != null) {
                        List<Departement> departements= departmentRepository.findAllByIdIn(event.getDepartments());
                        existingUser.setDepartments(departements);

                    }

                    userRepository.save(existingUser);
                }
            }
            case DELETED -> {
                userRepository.deleteById(event.getUserId());
            }
        }

    }
}
