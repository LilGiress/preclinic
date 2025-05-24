package com.medecineWebApp.Configuration.dto;


import com.medecineWebApp.Configuration.models.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO extends AuditableDTO {
    private Long id;
    private String street;
    private String postalCode;
    private CityDTO city;


}
