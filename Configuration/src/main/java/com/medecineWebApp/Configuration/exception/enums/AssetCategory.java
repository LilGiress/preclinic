package com.medecineWebApp.Configuration.exception.enums;

public enum AssetCategory {
    VEHICLE("#ff0000"),
    EQUIPMENT("#00ff00"),
    BUILDING("#0000ff");

    private String color;

    AssetCategory(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
