package com.medecineWebApp.Configuration.models.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users_session")
public class UserSession {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(columnDefinition = "VARCHAR(36)")
    private String sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String ipAddress;
    @Column(name = "navigateur")
    private String userAgent;
    @Column(name = "operating system")
    private String os;

    private LocalDateTime createdAt;

    private LocalDateTime lastAccessedAt;
    @Column(length = 512)
    private String fingerprint;
    @Column(name = "etat")
    private boolean active;
}
