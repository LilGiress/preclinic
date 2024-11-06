package com.medecineWebApp.Configuration.repository;

import com.medecineWebApp.Configuration.enums.AssetCategory;
import com.medecineWebApp.Configuration.models.setting.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByCategory(AssetCategory category);
}
