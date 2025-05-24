package com.medecineWebApp.Inventory_Service.dto;

import com.medecineWebApp.Inventory_Service.enums.InventoryStatus;
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
public class InventoryRecordDTO extends AuditableDTO{
    private Long id;
    private InventoryItemDTO item;
    private int countedQuantity;
    private LocalDateTime checkedAt;
    private Long checkedBy;
    @Enumerated(EnumType.STRING)
    private InventoryStatus status;



}
