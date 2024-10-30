package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.models.Leaves;
import com.medecineWebApp.Configuration.payload.request.LeaveRequest;


import java.util.List;
import java.util.Optional;

public interface LeaveService {
    List <Leaves> findAllLeaves();
    Optional<Leaves> findLeaveById(Long id);
    Leaves save(LeaveRequest leave);
    Leaves update(Long id,Leaves leave);
    void deleteById(Long id);

}
