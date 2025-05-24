package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.models.Auditable;
import com.medecineWebApp.Configuration.models.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VilleDTO  extends Auditable {
    private Long id;
    private String nom;
    private Region region;
}
