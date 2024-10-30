package com.medecineWebApp.Configuration.repository.role;

import com.medecineWebApp.Configuration.enums.RoleType;
import com.medecineWebApp.Configuration.models.role.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(RoleType name);

}
