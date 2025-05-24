package com.medecineWebApp.Inventory_Service.service.impl;

import com.medecineWebApp.Inventory_Service.dto.SupplierDTO;
import com.medecineWebApp.Inventory_Service.exception.SupplierNotFoundException;
import com.medecineWebApp.Inventory_Service.mapper.SupplierMapper;
import com.medecineWebApp.Inventory_Service.models.Supplier;
import com.medecineWebApp.Inventory_Service.repository.SupplierRepository;
import com.medecineWebApp.Inventory_Service.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public SupplierDTO save(Supplier supplier) {
        return supplierMapper.entityToDto(supplierRepository.save(supplier));
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(supplierMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException( "supplier not fund id"+ id));
        return supplierMapper.entityToDto(supplier);
    }

    @Override
    public void deleteSupplierById(Long id) {
        supplierRepository.deleteById(id);
    }
}
