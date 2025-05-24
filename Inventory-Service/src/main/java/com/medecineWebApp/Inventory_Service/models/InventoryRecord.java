package com.medecineWebApp.Inventory_Service.models;

import com.medecineWebApp.Inventory_Service.enums.InventoryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Table(name = "inventory_records")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class InventoryRecord extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private InventoryItem item;

    private int countedQuantity;
    private LocalDateTime checkedAt;
    @Column(name = "user_id")
    private Long checkedBy;
    @Enumerated(EnumType.STRING)
    private InventoryStatus status;

}
