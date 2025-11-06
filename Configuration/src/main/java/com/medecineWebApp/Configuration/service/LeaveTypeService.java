package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.LeaveTypeDTO;
import com.medecineWebApp.Configuration.enums.EntityStatus;
import com.medecineWebApp.Configuration.models.LeaveType;
import com.medecineWebApp.Configuration.payload.request.LeaveTypeRequest;

import java.util.List;
import java.util.Optional;

public interface LeaveTypeService {
    LeaveTypeDTO save(LeaveTypeRequest leaveTypeRequest);
     Optional<LeaveTypeDTO> getLeaveTypeById(Long id);
    List<LeaveTypeDTO> getAllLeaveTypes();
    LeaveTypeDTO updateLeaveType(Long id, LeaveType leaveType);
    void deleteLeaveType(Long id);
    boolean changeStatus(Long id, String status);
}
