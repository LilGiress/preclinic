package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.LeavesDTO;
import com.medecineWebApp.Configuration.enums.LeaveStatus;
import com.medecineWebApp.Configuration.models.Leaves;
import com.medecineWebApp.Configuration.payload.request.LeaveRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LeaveService {
    Page<LeavesDTO> findAllLeaves(Long employeeId, LeaveStatus status,
                                  LocalDate startFrom, LocalDate endBefore,
                                  Long leaveTypeId, Pageable pageable);
    Optional<LeavesDTO> findLeaveById(Long id);
    LeavesDTO save(LeaveRequest leave);
    LeavesDTO update(Long id, Leaves leave);
    void deleteById(Long id);

    boolean changeStatus(Long id,String status);
}
