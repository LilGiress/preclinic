package com.medecineWebApp.Finance_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemDTO extends AuditableDTO {
    private Long id;
    private String itemName;
    private String description;
    private Double unitCost;
    private Integer quantity;
    private Double amount;
    private InvoiceDTO invoice;
}
