package com.medecineWebApp.Configuration.repository;


import com.medecineWebApp.Configuration.models.City;
import com.medecineWebApp.Configuration.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
   Optional<Country> findByName(String name);

}
