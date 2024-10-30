package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.models.LeaveType;
import com.medecineWebApp.Configuration.payload.request.LeaveTypeRequest;
import com.medecineWebApp.Configuration.repository.leaves.LeaveTypeRepository;
import com.medecineWebApp.Configuration.service.LeaveTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {
    private final LeaveTypeRepository leaveTypeRepository;

    public LeaveTypeServiceImpl(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public LeaveType save(LeaveTypeRequest leaveTypeRequest) {
        LeaveType leaveType = new LeaveType();
        leaveType.setLeaveType(leaveTypeRequest.getLeaveType());
        leaveType.setLeaveDays(leaveTypeRequest.getLeaveDays());
        leaveType.setStatus(leaveTypeRequest.getStatus());
        return  leaveTypeRepository.save(leaveType);
    }

    @Override
    public Optional<LeaveType> getLeaveTypeById(Long id) {
        return leaveTypeRepository.findById(id);
    }

    @Override
    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeRepository.findAll();
    }

    @Override
    public LeaveType updateLeaveType(Long id, LeaveType leaveType) {
        Optional<LeaveType> leaveTypeOptional = leaveTypeRepository.findById(id);
        LeaveType updatedLeaveType = leaveTypeOptional.get();
        if (leaveTypeOptional.isPresent()) {
            updatedLeaveType.setLeaveType(leaveType.getLeaveType());
            updatedLeaveType.setLeaveDays(leaveType.getLeaveDays());
            updatedLeaveType.setStatus(leaveType.getStatus());
        }
        return null;
    }

    @Override
    public void deleteLeaveType(Long id) {
        leaveTypeRepository.deleteById(id);
    }
}
