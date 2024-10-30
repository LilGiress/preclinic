package com.medecineWebApp.Configuration.token;

import com.medecineWebApp.Configuration.enums.TokenType;
import com.medecineWebApp.Configuration.models.user.User;
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
        public TokenType tokenType = TokenType.BEARER;
        private LocalDateTime createdAt;
        private LocalDateTime expiresAt;
        private LocalDateTime validatedAt;
        private boolean revoked;
        private boolean isexpired;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_Id", nullable = false)
        private User user;

    }


