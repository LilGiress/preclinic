package com.medecineWebApp.Configuration.service.impl;

import com.medecineWebApp.Configuration.dto.AddressDTO;
import com.medecineWebApp.Configuration.mapper.AddressMapper;
import com.medecineWebApp.Configuration.models.Address;
import com.medecineWebApp.Configuration.models.City;
import com.medecineWebApp.Configuration.repository.AddressRepository;
import com.medecineWebApp.Configuration.repository.CityRepository;
import com.medecineWebApp.Configuration.repository.CountryRepository;
import com.medecineWebApp.Configuration.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, CityRepository cityRepository, CountryRepository countryRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDTO saveAddress(Address addressDTO) {
        City city = cityRepository.findByCity(addressDTO.getCity().getCity())
                .orElseThrow(() -> new RuntimeException("City not found"));

//        Country country = countryRepository.findByName(addressDTO.getCountry().getName())
//                .orElseThrow(() -> new RuntimeException("Country not found"));

        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCity(city);
       // address.setCountry(country);

        return addressMapper.addressToAddressDTO(addressRepository.save(address));
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(addressMapper::addressToAddressDTO).collect(Collectors.toList());
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        return addressMapper.addressToAddressDTO(address);
    }
}
