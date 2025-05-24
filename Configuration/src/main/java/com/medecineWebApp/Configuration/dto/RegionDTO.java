package com.medecineWebApp.Configuration.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.medecineWebApp.Configuration.models.Auditable;
import com.medecineWebApp.Configuration.models.City;
import com.medecineWebApp.Configuration.models.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionDTO extends AuditableDTO {
    private Long id;
    private String name;
    private Country country;
    private List<City> villes;
}
