package com.medecineWebApp.Inventory_Service.models;

import com.medecineWebApp.Inventory_Service.enums.MovementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Table(name = "stock_movement")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class StockMovement extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consumable_id")
    private MedicalConsumable consumable;

    private int quantity;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private MovementType movementType; // IN, OUT
    private Long patientId;
    private Long supplierId;

}
