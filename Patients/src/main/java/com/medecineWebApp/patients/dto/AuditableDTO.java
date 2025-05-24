package com.medecineWebApp.patients.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditableDTO {
    protected String createdBy;


    protected Instant createdDate;


    protected String lastModifiedBy;


    protected Instant lastModifiedDate;
}
