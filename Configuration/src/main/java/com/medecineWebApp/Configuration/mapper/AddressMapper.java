package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.AddressDTO;
import com.medecineWebApp.Configuration.models.user.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    AddressDTO addressToAddressDTO(Address address);
    Address addressDTOToAddress(AddressDTO addressDTO);
}
