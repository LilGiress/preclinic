package com.medecineWebApp.Asset.Management.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuditableDTO {
    protected String createdBy;

    protected Instant createdDate;

    protected String lastModifiedBy;

    protected Instant lastModifiedDate;
}
