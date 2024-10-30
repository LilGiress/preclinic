package com.medecineWebApp.Configuration.models.setting;

import com.medecineWebApp.Configuration.models.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name = "themeSetting")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ThemeSetting extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId; // ID of the user

    @Column(nullable = false)
    private String theme; // e.g., "LIGHT", "DARK", "CUSTOM"

    private String websiteName;  // Name of the website (customizable)

    private String lightLogoUrl; // URL for the light theme logo

    private String faviconUrl;   // URL for the favicon
}
