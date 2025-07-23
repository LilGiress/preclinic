package com.medecineWebApp.Inventory_Service.mapper;

import com.medecineWebApp.Inventory_Service.dto.StockDTO;
import com.medecineWebApp.Inventory_Service.models.Stock;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StockMapper {
    Stock toStock(StockDTO stockDTO);
    @InheritInverseConfiguration
    StockDTO toStockDTO(Stock stock);

}
