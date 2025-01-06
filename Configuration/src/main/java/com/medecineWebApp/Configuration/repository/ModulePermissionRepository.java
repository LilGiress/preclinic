package com.medecineWebApp.Configuration.repository;

import com.medecineWebApp.Configuration.models.ModulePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<ModulePermission, Long> {
}
