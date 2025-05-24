package com.medecineWebApp.Finance_service.mapper;

import com.medecineWebApp.Finance_service.dto.PaymentDTO;
import com.medecineWebApp.Finance_service.models.Payment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @InheritInverseConfiguration
    Payment fromDTO(PaymentDTO dto);
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    PaymentDTO toDTO(Payment item);
}
