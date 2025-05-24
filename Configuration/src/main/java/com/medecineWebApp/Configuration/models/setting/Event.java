package com.medecineWebApp.Configuration.models.setting;

import com.medecineWebApp.Configuration.enums.EventCategory;
import com.medecineWebApp.Configuration.models.Auditable;
import com.medecineWebApp.Configuration.models.Holiday;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Event extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime eventDate;

    @Enumerated(EnumType.STRING)
    private EventCategory category;
    @ManyToOne
    @JoinColumn(name = "calendar_id", nullable = false)
    private Calendar calendar;

    // Optional: Link to a patient, doctor, or other entities
    private Long relatedUserId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holiday_id")
    private Holiday holiday; // Le jour férié associé à cet événement


}
