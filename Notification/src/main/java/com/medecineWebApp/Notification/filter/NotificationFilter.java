package com.medecineWebApp.Notification.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class NotificationFilter {
    private String userName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private String status;
}
