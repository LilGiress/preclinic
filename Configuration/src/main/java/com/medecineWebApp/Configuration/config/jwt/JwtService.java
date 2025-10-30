package com.medecineWebApp.Configuration.config.jwt;

import com.medecineWebApp.Configuration.models.role.ActionPermission;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.models.user.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Service
public class JwtService {

    private final long jwtExpiration;
    private final String jwtSecret;
    private final long refreshExpiration;
    @Autowired
    public JwtService(@Value("${security.jwt.expiration}")long jwtExpiration, @Value("${security.jwt.refresh-token.expiration}")long refreshExpiration, @Value("${security.jwt.secret}")String jwtSecret) {
        this.jwtExpiration = jwtExpiration;
        this.refreshExpiration = refreshExpiration;
        this.jwtSecret = jwtSecret;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }

    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expirationTime) {
        var authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        List<String> roles = Collections.emptyList();
        List<Map<String, Object>> permissionsJson = Collections.emptyList();

        if (userDetails instanceof Users user) {
            roles = user.getRoles().stream()
                    .map(Roles::getName)
                    .toList();

            // Construire les permissions structurées
             permissionsJson = user.getRoles().stream()
                    .flatMap(role -> role.getPermissions().stream())
                    .map(permission -> Map.of(
                            "module", permission.getLabel(),
                            "actions", permission.getActions().stream()
                                    .filter(ActionPermission::isSelected)
                                    .map(ActionPermission::getLabel)
                                    .toList()
                    ))
                    .filter(map -> !((List<?>) map.get("actions")).isEmpty())
                    .toList();
        }

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .claim("authorities", authorities)
                .claim("roles", roles)
                .claim("permissions", permissionsJson)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public List<String> extractRole(String token) {
        Object rawRoles = extractAllClaims(token).get("roles");
        if (rawRoles instanceof List<?>) {
            return ((List<?>) rawRoles).stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();

    }

    public List<String> extractPermissions(String token) {
        return extractAllClaims(token).get("permissions", List.class);
    }
}
