package com.medecineWebApp.Configuration.config.jwt;

import com.medecineWebApp.Configuration.repository.token.TokenRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    @Autowired
    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService, TokenRepository tokenRepository) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          @NonNull HttpServletResponse response,
          @NonNull FilterChain filterChain) throws ServletException, IOException {
            if (request.getServletPath().contains("/api/auth")) {
                filterChain.doFilter(request, response);
                return;
            }

            final String authHeader = request.getHeader(AUTHORIZATION);
            final String jwt;
            final String userEmail;
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }


        try {
            jwt = authHeader.substring(7);
            String username = jwtService.extractUsername(jwt);
            String role = jwtService.extractRole(jwt);
            List<String> permissions = jwtService.extractPermissions(jwt);

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role)); // Ajouter le rôle
            permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission))); // Ajouter les permissions

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (JwtException e) {
            logger.error("JWT invalide : " + e.getMessage());
        }


//            userEmail = jwtService.extractUsername(jwt);
//            if (userEmail == null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails= userDetailsService.loadUserByUsername(userEmail);
//
//                var isTokenValid = tokenRepository.findByToken(jwt)
//                        .map(t -> !t.isIsexpired() && !t.isRevoked())
//                        .orElse(false);
//
//
//                if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
//                    UsernamePasswordAuthenticationToken autheToken = new UsernamePasswordAuthenticationToken(
//                            userDetails,null,userDetails.getAuthorities()
//                    );
//                    autheToken.setDetails(
//                            new WebAuthenticationDetailsSource().buildDetails(request)
//                    );
//                    SecurityContextHolder.getContext().setAuthentication(autheToken);
//                }
//            }
            filterChain.doFilter(request, response);
    }
}
