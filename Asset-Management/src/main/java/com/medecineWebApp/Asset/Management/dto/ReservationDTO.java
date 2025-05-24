package com.medecineWebApp.Asset.Management.dto;

import com.medecineWebApp.Asset.Management.enums.ReservationStatus;
import com.medecineWebApp.Asset.Management.models.Assets;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO extends AuditableDTO{
    private Long id;
    private Long doctorId;

    private Assets asset;

    private LocalDateTime reservationDate;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
