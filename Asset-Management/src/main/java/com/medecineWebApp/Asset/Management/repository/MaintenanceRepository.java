package com.medecineWebApp.Asset.Management.repository;

import com.medecineWebApp.Asset.Management.models.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
