package com.medecineWebApp.Configuration.repository;


import com.medecineWebApp.Configuration.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
