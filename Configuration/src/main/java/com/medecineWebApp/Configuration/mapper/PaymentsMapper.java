package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.PaymentsDTO;
import com.medecineWebApp.Configuration.models.accounts.Payments;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentsMapper {
    PaymentsMapper INSTANCE = Mappers.getMapper(PaymentsMapper.class);
    PaymentsDTO paymentsToPaymentsDTO(Payments payments);
    Payments paymentsDTOToPayments(PaymentsDTO paymentsDTO);
}
