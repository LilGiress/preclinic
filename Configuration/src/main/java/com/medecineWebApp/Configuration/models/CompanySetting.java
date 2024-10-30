package com.medecineWebApp.Configuration.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "config_company_Setting")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CompanySetting extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String companyAddress;
    @Column(unique = true)
    private String companyEmail;
    private String companyPhone;
    private String companyWebsite;
    private String companyFax;
    private String companyPostalCode;
    private String companyCountry;
    private String companyState;

}
