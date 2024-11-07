package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.LeavesDTO;
import com.medecineWebApp.Configuration.models.Leaves;
import com.medecineWebApp.Configuration.payload.request.LeaveRequest;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

public interface LeaveService {
    Page<LeavesDTO> findAllLeaves(int page, int size);
    Optional<LeavesDTO> findLeaveById(Long id);
    LeavesDTO save(LeaveRequest leave);
    LeavesDTO update(Long id, Leaves leave);
    void deleteById(Long id);

}
