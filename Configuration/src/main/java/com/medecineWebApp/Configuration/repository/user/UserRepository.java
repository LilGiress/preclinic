package com.medecineWebApp.Configuration.repository.user;


import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.models.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    boolean existsByFirstnameOrLastname(String firstName, String lastName);
    boolean existsByEmail(String email);
    Optional<Users> findByUsername(String username);
    List<Users> findUsersByRoles(Set<Roles> roles);
}
