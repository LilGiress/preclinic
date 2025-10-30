package com.medecineWebApp.Configuration.repository.permission;

import com.medecineWebApp.Configuration.models.role.ActionPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionPermissionRepository extends JpaRepository<ActionPermission, Long> {
    Optional<ActionPermission> findByLabel(String label);
}
