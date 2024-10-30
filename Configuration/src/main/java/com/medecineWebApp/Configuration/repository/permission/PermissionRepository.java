package com.medecineWebApp.Configuration.repository.permission;

import com.medecineWebApp.Configuration.models.role.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
