package com.medecineWebApp.Inventory_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO extends AuditableDTO {
    private Long id;
    private String name;
    private String contactInfo;
}
