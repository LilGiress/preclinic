package com.medecineWebApp.Configuration.payload.request;

import com.medecineWebApp.Configuration.models.LeaveType;
import lombok.*;


import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {
    private LocalDate startDate;

    private LocalDate endDate;

    private String leaveReason;

    private LeaveType leaveType;
}
