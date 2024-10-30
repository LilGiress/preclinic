package com.medecineWebApp.Configuration.exception.enums;

public enum EventCategory {
    WORK("#ff0000"),
    PERSONAL("#00ff00"),
    HOLIDAY("#0000ff");

    private String color;

    EventCategory(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
