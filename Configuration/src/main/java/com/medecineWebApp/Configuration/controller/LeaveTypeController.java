package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.LeaveTypeDTO;
import com.medecineWebApp.Configuration.models.LeaveType;
import com.medecineWebApp.Configuration.payload.request.LeaveTypeRequest;
import com.medecineWebApp.Configuration.service.LeaveTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leavetype")
public class LeaveTypeController {
    private final LeaveTypeService leaveTypeService;


    public LeaveTypeController(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;

    }
    @PostMapping("/create")
    public ResponseEntity<LeaveTypeDTO> createLeaves(LeaveTypeRequest leaveTypeRequest) {
        return ResponseEntity.ok(leaveTypeService.save(leaveTypeRequest));

    }

    @GetMapping("")
    public ResponseEntity <List<LeaveTypeDTO>> getAllLeaves(
    ) {
        return ResponseEntity.ok(leaveTypeService.getAllLeaveTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LeaveTypeDTO>>getLeaveById(@PathVariable Long id) {
        return ResponseEntity.ok(leaveTypeService.getLeaveTypeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveTypeDTO> updateLeaveById(@PathVariable Long id, @RequestBody LeaveType leaveType) {
        return ResponseEntity.ok(leaveTypeService.updateLeaveType(id, leaveType));
    }

    @DeleteMapping("/{id}")
    public void deleteLeaveById(@PathVariable Long id) {
        leaveTypeService.deleteLeaveType(id);
    }
}
