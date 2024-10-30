package com.medecineWebApp.Configuration.models.setting;


import com.medecineWebApp.Configuration.models.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Entity
@Table(name = "SmtpConfig")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SmtpConfig extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String smtpHost;
    private int smtpPort;
    private String smtpUser;
    private String smtpPassword;
}
