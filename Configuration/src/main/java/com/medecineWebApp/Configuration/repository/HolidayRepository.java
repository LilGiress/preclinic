package com.medecineWebApp.Configuration.repository;

import com.medecineWebApp.Configuration.models.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    List<Holiday> findByDate(LocalDate date);

    // Vous pouvez aussi ajouter d'autres méthodes de recherche comme:
    List<Holiday> findByNameContaining(String name); // Recherche par nom

    // Trouver les jours fériés entre deux dates
    List<Holiday> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
