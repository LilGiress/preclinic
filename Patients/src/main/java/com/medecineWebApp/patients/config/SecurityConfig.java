package com.medecineWebApp.patients.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // Désactiver CSRF (si vous utilisez des tokens JWT)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/employee/**").authenticated()  // Sécuriser les requêtes vers "/employee/**"
                        .anyRequest().permitAll()  // Autoriser toutes les autres requêtes
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Pas de session, authentification via JWT
                );// Pas de session HTTP, uniquement des tokens JWT

        // Ajouter le filtre JWT avant le filtre de gestion des authentifications
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();  // Construire l'objet SecurityFilterChain
    }
}
