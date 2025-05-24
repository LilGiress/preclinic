package com.medecineWebApp.Configuration.repository.role;

import com.medecineWebApp.Configuration.models.role.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String name);
    Set<Roles> findByNameIn(List<String> names);

    Set<Roles> findAllByIdIn(Collection<Long> ids);

}
