package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.models.Leaves;
import com.medecineWebApp.Configuration.payload.request.LeaveRequest;
import com.medecineWebApp.Configuration.repository.leaves.LeavesRepository;
import com.medecineWebApp.Configuration.service.LeaveService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LeavesServiceImpl implements LeaveService {
    private final LeavesRepository leavesRepository;

    public LeavesServiceImpl(LeavesRepository leavesRepository) {
        this.leavesRepository = leavesRepository;
    }

    @Override
    public List<Leaves> findAllLeaves() {
        return leavesRepository.findAll();
    }

    @Override
    public Optional<Leaves> findLeaveById(Long id) {
        return leavesRepository.findById(id);
    }

    @Override
    public Leaves save(LeaveRequest leave) {
        Leaves leaves = new Leaves();
        leaves.setStartDate(leave.getStartDate());
        leaves.setEndDate(leave.getEndDate());
        leaves.setLeaveReason(leave.getLeaveReason());
        leaves.setLeaveType(leave.getLeaveType());
        return leavesRepository.save(leaves);
    }

    @Override
    public Leaves update(Long id,Leaves leave) {
        Optional<Leaves> optionalLeaves = leavesRepository.findById(id);
        if (optionalLeaves.isPresent()) {
            Leaves updatedLeave = optionalLeaves.get();
            updatedLeave.setLeaveReason(leave.getLeaveReason());
            updatedLeave.setLeaveType(leave.getLeaveType());
            updatedLeave.setStartDate(leave.getStartDate());
            updatedLeave.setEndDate(leave.getEndDate());
            return leavesRepository.save(updatedLeave);
        }
        throw new RuntimeException("Leaves with id " + id + " not found");
    }

    @Override
    public void deleteById(Long id) {
        leavesRepository.deleteById(id);
    }
}
