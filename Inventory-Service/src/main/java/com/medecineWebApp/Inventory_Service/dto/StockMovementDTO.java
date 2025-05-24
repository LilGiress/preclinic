package com.medecineWebApp.Inventory_Service.dto;

import com.medecineWebApp.Inventory_Service.enums.MovementType;
import com.medecineWebApp.Inventory_Service.models.MedicalConsumable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockMovementDTO extends AuditableDTO {
    private Long id;
    private MedicalConsumableDTO consumable;
    private int quantity;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private MovementType movementType; // IN, OUT
    private Long patientId;

}
