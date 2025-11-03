package com.medecineWebApp.Configuration.models.role;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String description;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "permission_action", // nom de la table d'association
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "action_id")
    )
    @JsonIgnore
    private List<ActionPermission> actions = new ArrayList<>();  // Toutes les actions liées au module


    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonBackReference
    private Roles role;

    public Permission(String label, String description, List<ActionPermission> actions) {
        this.label = label;
        this.description = description;
        this.actions = actions != null ? actions : new ArrayList<>();
    }

    // Méthode utilitaire pour ajouter une action et lier la permission
    public void addAction(ActionPermission action) {
        if (!this.actions.contains(action)) {
            this.actions.add(action);
            action.getPermissions().add(this);
        }
    }

    // ✅ Méthode utilitaire inverse
    public void removeAction(ActionPermission action) {
        this.actions.remove(action);
        action.getPermissions().remove(this);
    }

}
