package com.medecineWebApp.Accounts.repositories;

import com.medecineWebApp.Accounts.models.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface InvoicesRepository extends JpaRepository<Invoices, Long>, JpaSpecificationExecutor<Invoices> {
    Optional<Invoices> findTopByOrderByIdDesc();
}
