package com.medecineWebApp.Configuration.repository.user;


import com.medecineWebApp.Configuration.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByFirstnameOrLastname(String firstName, String lastName);

    boolean existsByEmail(String email);


    User findByUsername(String username);
}
