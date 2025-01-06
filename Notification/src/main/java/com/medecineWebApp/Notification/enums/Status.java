package com.medecineWebApp.Accounts.enums;

public enum Status {
    PENDING,         // The task or request is awaiting action
    APPROVED,        // The task or request has been approved
    REJECTED,        // The task or request has been rejected
    IN_PROGRESS,     // The task is currently being worked on
    COMPLETED,       // The task or request has been completed
    CANCELED,        // The task or request was canceled
    ON_HOLD,         // The task or request is put on hold
    FAILED,          // The task failed or could not be processed
    REVIEW
}
