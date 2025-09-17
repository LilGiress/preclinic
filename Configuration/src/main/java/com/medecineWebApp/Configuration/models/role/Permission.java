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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "permission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Permission extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String description;
    private boolean isSelected;
    private boolean disabled;


    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ActionPermission> actions = new ArrayList<>();  // Toutes les actions liées au module


    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonBackReference
    private Roles role;

    public Permission(String label, String description, boolean isSelected, boolean disabled, List<ActionPermission> actions) {
        this.label = label;
        this.description = description;
        this.isSelected = isSelected;
        this.disabled = disabled;
        this.actions = actions != null ? actions : new ArrayList<>();
    }

    // Méthode utilitaire pour ajouter une action et lier la permission
    public void addAction(ActionPermission action) {
        actions.add(action);
        action.setPermission(this);
    }

}
