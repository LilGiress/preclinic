package com.medecineWebApp.Configuration.models.role;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.medecineWebApp.Configuration.models.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
@Entity
@Table(name = "action_permission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ActionPermission extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;       // Exemple: "CAN_READ", "CAN_WRITE"
    private boolean isSelected;
    private boolean disabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    @JsonBackReference
    private Permission permission;

    public ActionPermission(String label, boolean isSelected, boolean disabled) {
        this.label = label;
        this.isSelected = isSelected;
        this.disabled = disabled;
    }

}
