package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.models.LeaveType;
import com.medecineWebApp.Configuration.payload.request.LeaveTypeRequest;

import java.util.List;
import java.util.Optional;

public interface LeaveTypeService {
    LeaveType save(LeaveTypeRequest leaveTypeRequest);
     Optional<LeaveType> getLeaveTypeById(Long id);
    List<LeaveType> getAllLeaveTypes();
    LeaveType updateLeaveType( Long id,LeaveType leaveType);
    void deleteLeaveType(Long id);
}
