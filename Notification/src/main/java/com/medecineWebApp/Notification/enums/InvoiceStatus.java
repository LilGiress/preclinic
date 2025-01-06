package com.medecineWebApp.Accounts.enums;

public enum InvoiceStatus {
    // Invoice has been created but not yet issued to the customer
    DRAFT,

    // Invoice has been sent to the customer but not yet paid
    SENT,

    // Invoice has been partially paid
    PARTIALLY_PAID,

    // Invoice has been fully paid
    PAID,

    // Invoice has been canceled and is no longer valid
    CANCELED,

    // Invoice is overdue and requires immediate action
    OVERDUE,

    // Invoice has been disputed by the customer
    DISPUTED,

    // Payment has failed (e.g., due to insufficient funds)
    PAYMENT_FAILED,

    // Invoice has been refunded to the customer
    REFUNDED,

    // Invoice has been archived after processing (optional state)
    ARCHIVED
}
