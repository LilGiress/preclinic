package com.medecineWebApp.Configuration.mapper;


import com.medecineWebApp.Configuration.dto.AddressDTO;
import com.medecineWebApp.Configuration.models.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    AddressDTO addressToAddressDTO(Address address);
    @InheritInverseConfiguration
    Address addressDTOToAddress(AddressDTO addressDTO);
}
