package com.medecineWebApp.Asset.Management.repository;

import com.medecineWebApp.Asset.Management.models.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetsRepository extends JpaRepository<Assets, Long> {
    // Utilisation d'une requête JPQL personnalisée
  //  @Query("SELECT a FROM Assets a WHERE a.maintenanceRequired = true")
  //  List<Assets> findAssetsNeedingMaintenanceRequiredTrue();
    List<Assets> findByMaintenanceRequiredTrue();

}
