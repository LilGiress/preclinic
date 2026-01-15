package com.medecineWebApp.Configuration.models;

import com.medecineWebApp.Configuration.models.setting.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Holidays")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class,AuditLogListener.class})
public class Holiday extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;
    @OneToMany(mappedBy = "holiday", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events; // Liste des événements associés à ce jour férié
}
