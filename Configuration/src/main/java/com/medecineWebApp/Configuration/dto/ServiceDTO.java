package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.models.Groupe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private Long id;
    private String name;
    private Set<Groupe> groupes;
}
