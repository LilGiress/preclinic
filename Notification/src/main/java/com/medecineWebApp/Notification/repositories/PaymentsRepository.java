package com.medecineWebApp.Accounts.repositories;

import com.medecineWebApp.Accounts.models.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {
}
