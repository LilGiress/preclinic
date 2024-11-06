package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.models.user.Country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String street;
    private String city;
    private String postalCode;
    private Country country;
}
