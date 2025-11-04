package com.medecineWebApp.Configuration.payload.request;


import com.medecineWebApp.Configuration.enums.EntityStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTypeRequest {
    private String leaveType;
    private Integer leaveDays;
    private EntityStatus status;
}
