package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.models.Leaves;
import com.medecineWebApp.Configuration.payload.request.LeaveRequest;
import com.medecineWebApp.Configuration.service.LeaveService;
import com.medecineWebApp.Configuration.service.impl.LeavesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;
    @PostMapping("/create")
    public ResponseEntity<Leaves> createLeaves(LeaveRequest leaveRequest) {
        return ResponseEntity.ok(leaveService.save(leaveRequest));

    }

    @GetMapping("")
    public ResponseEntity <List<Leaves>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.findAllLeaves());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Leaves>>getLeaveById(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.findLeaveById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Leaves> updateLeaveById(@PathVariable Long id, @RequestBody Leaves leaves) {
        return ResponseEntity.ok(leaveService.update(id, leaves));
    }

    @DeleteMapping("/{id}")
    public void deleteLeaveById(@PathVariable Long id) {
        leaveService.deleteById(id);
    }
}
