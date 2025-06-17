package com.medecineWebApp.Configuration.config.jwt;

import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.models.user.Users;
import io.jsonwebtoken.Claims;
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
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
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
        List<String> permissions = Collections.emptyList();

        if (userDetails instanceof Users user) {
            roles = user.getRoles().stream()
                    .map(Roles::getName)
                    .toList();

            permissions = user.getRoles().stream()
                    .flatMap(role -> role.getPermissions().stream())
                    .flatMap(permission -> {
                        List<String> perms = new ArrayList<>();
                        if (permission.isCanRead()) perms.add("canRead");
                        if (permission.isCanWrite()) perms.add("canWrite");
                        if (permission.isCanCreate()) perms.add("canCreate");
                        if (permission.isCanDelete()) perms.add("canDelete");
                        if (permission.isCanImport()) perms.add("canImport");
                        if (permission.isCanExport()) perms.add("canExport");
                        if (permission.isCanApprove()) perms.add("canApprove");
                        if (permission.isCanValidate()) perms.add("canValidate");
                        if (permission.isCanAssign()) perms.add("canAssign");
                        if (permission.isCanGenerateReport()) perms.add("canGenerateReport");
                        if (permission.isCanActivate()) perms.add("canActivate");
                        return perms.stream();
                    })
                    .toList();
        }

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .claim("authorities", authorities)
                .claim("roles", roles)
                .claim("permissions", permissions)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


//    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expirationTime) {
//        var authorities = userDetails.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList();
//
//        return Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
//                .claim("authorities", authorities)
//                .claim("roles", userDetails instanceof Users ?
//                        ((Users) userDetails).getRoles().stream()  // Stream des rôles
//                                .map(Roles::getName)  // Mapper chaque rôle en son nom
//                                .toList() // Collecter les noms des rôles dans une liste
//                        : null)  // Si ce n'est pas un Users, renvoyer null
//                .claim("permissions", userDetails instanceof Users ?
//                        ((Users) userDetails).getRoles().stream() // Récupérer les rôles de l'utilisateur
//                                .flatMap(role -> role.getPermissions().stream()) // Récupérer les permissions associées aux rôles
//                                .flatMap(permission -> { // Pour chaque permission, crée une liste de chaînes représentant les actions possibles
//                                    List<String> permissions = new ArrayList<>();
//                                    if (permission.isCanRead()) permissions.add("canRead");
//                                    if (permission.isCanWrite()) permissions.add("canWrite");
//                                    if (permission.isCanCreate()) permissions.add("canCreate");
//                                    if (permission.isCanDelete()) permissions.add("canDelete");
//                                    if (permission.isCanImport()) permissions.add("canImport");
//                                    if (permission.isCanExport()) permissions.add("canExport");
//                                    if (permission.isCanApprove()) permissions.add("canApprove");
//                                    if (permission.isCanValidate()) permissions.add("canValidate");
//                                    if (permission.isCanAssign()) permissions.add("canAssign");
//                                    if (permission.isCanGenerateReport()) permissions.add("canGenerateReport");
//                                    if (permission.isCanActivate()) permissions.add("canActivate");
//                                    return permissions.stream(); // Retourner le flux de permissions sous forme de chaînes
//                                })
//                                .toList() // Convertir en liste
//                        : Collections.emptyList()) // Retourner une liste vide si ce n'est pas un `Users`
//
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // ✅ Ajout du second paramètre obligatoire
//                .compact();
//    }

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
