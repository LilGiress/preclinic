package com.medecineWebApp.Configuration.events;

import com.medecineWebApp.Configuration.models.user.User;
import com.medecineWebApp.Configuration.repository.user.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventListener {
    private final UserRepository userRepository;

    public UserEventListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
@KafkaListener(topics = "employee-events",groupId = "configuration-service-group")
    public void handleEmployeeEvent(EmployeeEvent event) {
        switch (event.getEventType()) {
            case CREATED -> {
                User user = new User();
                user.setId(event.getUserId());
                user.setRoles(event.getRoles());
                user.setDepartments(event.getDepartments());
                user.setUsername(event.getUsername());
                user.setFirstname(event.getFirstname());
                user.setLastname(event.getLastname());
                user.setEmail(event.getEmail());
                user.setPassword(event.getPassword());
                userRepository.save(user);

            }
            case UPDATED -> {

                User existingUser = userRepository.findById(event.getUserId()).orElse(null);
                if (existingUser != null) {
                    existingUser.setFirstname(event.getFirstname());
                    existingUser.setLastname(event.getLastname());
                    existingUser.setEmail(event.getEmail());
                    existingUser.setPassword(event.getPassword());
                    existingUser.setRoles(event.getRoles());
                    existingUser.setDepartments(event.getDepartments());
                    userRepository.save(existingUser);
                }
            }
            case DELETED -> {
                userRepository.deleteById(event.getUserId());
            }
        }

    }
}
