package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.models.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO extends AuditableDTO {
    private Long id;
    private List<RegionDTO> regions;
}
