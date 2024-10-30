package com.medecineWebApp.Configuration.repository.leaves;

import com.medecineWebApp.Configuration.models.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeavesRepository extends JpaRepository<Leaves, Long> {
}
