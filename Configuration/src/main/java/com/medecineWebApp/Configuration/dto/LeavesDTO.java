package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.models.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LeavesDTO {
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String leaveReason;

    private LeaveType leaveType;
}
