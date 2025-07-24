package com.medecineWebApp.Configuration.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.medecineWebApp.Configuration.enums.EntityStatus;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners({AuditingEntityListener.class,AuditLogListener.class})
public class Departement extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @JsonProperty("nom")
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Services> services = new ArrayList<>();

    private Long userId;

    private Long leaveId;


}
