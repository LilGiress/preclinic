package com.medecineWebApp.Asset.Management.models;

import com.medecineWebApp.Asset.Management.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Reservation extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long doctorId;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Assets asset;

    private LocalDateTime reservationDate;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status; // "Confirmée", "Annulée"
}
