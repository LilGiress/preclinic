package com.medecineWebApp.Configuration.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO extends AuditableDTO {
    private Long id;
    private String city;
    private RegionDTO region;
    private List<AddressDTO> addresses;
}
