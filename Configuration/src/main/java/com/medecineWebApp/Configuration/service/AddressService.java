package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.AddressDTO;
import com.medecineWebApp.Configuration.models.Address;

import java.util.List;

public interface AddressService {
    AddressDTO saveAddress(Address addressDTO);
    List<AddressDTO> getAllAddresses();
    AddressDTO getAddressById(Long id);
}
