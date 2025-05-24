package com.medecineWebApp.patients.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.List;

@Getter
@Component
public class JwtUtil {
    private final String secret;
    private final Key signingKey;
    @Autowired
    public JwtUtil(@Value("${security.jwt.secret}")String secret) {
        this.secret = secret;
        this.signingKey = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()  // Utiliser parserBuilder() au lieu de parser()
                .setSigningKey(signingKey)
                .build()  // Construire le parser
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().
                    setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

// ✅ Extraire les rôles depuis le token
    public List<String> extractRoles(String token) {
        return extractClaims(token).get("roles", List.class);
    }

    // ✅ Extraire les permissions depuis le token
    public List<String> extractPermissions(String token) {
        return extractClaims(token).get("permissions", List.class);
    }

    // ✅ Récupérer toutes les informations du token (username, roles, permissions, etc.)
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
