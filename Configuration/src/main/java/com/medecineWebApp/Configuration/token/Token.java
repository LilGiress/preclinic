package com.medecineWebApp.Configuration.token;

import com.medecineWebApp.Configuration.enums.TokenType;
import com.medecineWebApp.Configuration.models.user.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Token {
        @Id
        @GeneratedValue
        private Integer id;
        @Column(unique = true)
        private String token;
        @Enumerated(EnumType.STRING)
        public TokenType tokenType;
        private LocalDateTime createdAt;
        private LocalDateTime expiresAt;
        private LocalDateTime validatedAt;
        private boolean revoked;
        private boolean isexpired;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_Id", nullable = false)
        private Users users;

        public boolean isExpired() {
                return expiresAt.isBefore(LocalDateTime.now());
        }

    }


