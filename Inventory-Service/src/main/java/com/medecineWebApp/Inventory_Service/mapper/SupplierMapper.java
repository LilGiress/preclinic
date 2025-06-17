package com.medecineWebApp.Inventory_Service.mapper;

import com.medecineWebApp.Inventory_Service.dto.SupplierDTO;
import com.medecineWebApp.Inventory_Service.models.Supplier;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDTO entityToDto(Supplier supplier);
    @InheritInverseConfiguration
    Supplier entityToModel(SupplierDTO supplierDTO);
}
