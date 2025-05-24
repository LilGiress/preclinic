package com.medecineWebApp.Asset.Management.repository;

import com.medecineWebApp.Asset.Management.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
