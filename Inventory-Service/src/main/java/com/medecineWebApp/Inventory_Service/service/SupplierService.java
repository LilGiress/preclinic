package com.medecineWebApp.Inventory_Service.service;

import com.medecineWebApp.Inventory_Service.dto.SupplierDTO;
import com.medecineWebApp.Inventory_Service.models.Supplier;

import java.util.List;

public interface SupplierService {
    SupplierDTO save(Supplier supplier);
    List<SupplierDTO> getAllSuppliers();
    SupplierDTO getSupplierById(Long id);
    void deleteSupplierById(Long id);
}
