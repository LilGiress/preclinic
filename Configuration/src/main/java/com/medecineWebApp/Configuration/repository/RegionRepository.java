package com.medecineWebApp.Configuration.repository;

import com.medecineWebApp.Configuration.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    boolean existsByName(String name);
    List<Region> findByCountryId(Long countryId);
}
