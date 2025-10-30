package com.medecineWebApp.Configuration.models.role;

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
@Table(name = "action_permission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ActionPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;       // Exemple: "CAN_READ", "CAN_WRITE"
    private boolean selected;


    @ManyToMany(mappedBy = "actions", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Permission> permissions = new ArrayList<>();

    public ActionPermission(String label, boolean selected) {
        this.label = label;
        this.selected = selected;

    }

}
