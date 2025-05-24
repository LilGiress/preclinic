package com.medecineWebApp.Asset.Management.repository;

import com.medecineWebApp.Asset.Management.models.AssetMaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetMaintenanceLogRepository extends JpaRepository<AssetMaintenanceLog, Long> {
}
