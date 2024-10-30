package com.medecineWebApp.Configuration.repository.account;


import com.medecineWebApp.Configuration.models.accounts.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {
}
