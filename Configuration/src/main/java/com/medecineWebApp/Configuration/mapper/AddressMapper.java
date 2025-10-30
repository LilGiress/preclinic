package com.medecineWebApp.Configuration.mapper;


import com.medecineWebApp.Configuration.dto.AddressDTO;
import com.medecineWebApp.Configuration.models.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO addressToAddressDTO(Address address);
    @InheritInverseConfiguration
    Address addressDTOToAddress(AddressDTO addressDTO);
}
