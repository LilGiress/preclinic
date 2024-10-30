package com.medecineWebApp.Configuration.repository.leaves;

import com.medecineWebApp.Configuration.models.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
}
