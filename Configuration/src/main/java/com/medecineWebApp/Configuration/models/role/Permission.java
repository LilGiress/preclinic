package com.medecineWebApp.Configuration.models.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medecineWebApp.Configuration.models.Auditable;
import com.medecineWebApp.Configuration.models.Groupe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "Config_permission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Permission extends Auditable implements Serializable {
    @Id
    private String Name;
    @Column(columnDefinition = "TEXT")
    private String Description;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private List<Groupe> Groupes;

}
