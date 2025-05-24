package com.medecineWebApp.Inventory_Service.events;

import lombok.Getter;

@Getter
public enum UnitType {
    ML("Millilitre"),
    MG("Milligramme"),
    UNIT("Unité"),
    G("Gramme"),
    L("Litre"),
    CM("Centimètre"),
    MM("Millimètre"),
    KG("Kilogramme");

    private final String label;

    UnitType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
