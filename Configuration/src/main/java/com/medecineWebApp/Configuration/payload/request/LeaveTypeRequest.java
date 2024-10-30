package com.medecineWebApp.Configuration.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTypeRequest {
    private String leaveType;
    private Integer leaveDays;
    private String status;
}
