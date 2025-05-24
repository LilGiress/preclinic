package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.AddressDTO;
import com.medecineWebApp.Configuration.models.Address;
import com.medecineWebApp.Configuration.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @GetMapping("/all")
    public ResponseEntity <List<AddressDTO>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses()) ;
    }

    @PostMapping
    public ResponseEntity <AddressDTO> createAddress(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.saveAddress(address)) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity <AddressDTO> getAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressById(id)) ;
    }
}
