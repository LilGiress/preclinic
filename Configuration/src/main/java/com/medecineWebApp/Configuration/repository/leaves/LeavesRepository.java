package com.medecineWebApp.Configuration.repository.leaves;

import com.medecineWebApp.Configuration.models.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LeavesRepository extends JpaRepository <Leaves, Long>,JpaSpecificationExecutor<Leaves> {
}
