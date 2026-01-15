package com.medecineWebApp.Configuration.dto;


import com.medecineWebApp.Configuration.models.Departement;
import jakarta.persistence.Column;
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
    @Column(nullable = false)
    private String name;
    private String description;
    private Long medicalRecordId;
    private Departement departement;

}
