package com.medecineWepApp.api_gateway.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Component
public class JwtUtil {

    private final String secret;
    private final Key signingKey;
    @Autowired
    public JwtUtil(@Value("${security.jwt.secret}")String secret) {
        this.secret = secret;
        this.signingKey = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    }

    // Créer une clé à partir de la chaîne secrète
    public Key getSigningKey() {
        return signingKey;
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
}
