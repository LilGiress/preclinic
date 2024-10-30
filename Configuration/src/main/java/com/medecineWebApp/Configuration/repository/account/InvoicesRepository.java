package com.medecineWebApp.Configuration.repository.account;


import com.medecineWebApp.Configuration.models.accounts.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface InvoicesRepository extends JpaRepository<Invoices, Long>, JpaSpecificationExecutor<Invoices> {
    Optional<Invoices> findTopByOrderByIdDesc();
}
