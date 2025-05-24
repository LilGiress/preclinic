package com.medecineWebApp.Configuration.dto;


import com.medecineWebApp.Configuration.models.Departement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServicesDTO extends AuditableDTO {
    private Long id;
    private String name;
    private Long medicalRecordId;
    private Departement departement;

}
