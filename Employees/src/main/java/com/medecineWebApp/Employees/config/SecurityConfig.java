package com.medecineWebApp.Employees.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
        http.csrf(AbstractHttpConfigurer::disable)  // Désactiver CSRF (si vous utilisez des tokens JWT)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/employee/**").permitAll()
                        .requestMatchers("/admin/**").authenticated()  //.hasRole("ADMIN") // Accès uniquement aux ADMIN
                        .requestMatchers("/user/**").authenticated() //.hasAnyRole("USER", "ADMIN") // Accès USER et ADMIN
                        .requestMatchers("/patients/**").authenticated() //.hasAuthority("CAN_VIEW_PATIENTS") // Vérifie une permission spécifique// Sécuriser les requêtes vers "/employee/**"
                        .anyRequest().permitAll()  // Autoriser toutes les autres requêtes
                        //.anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Pas de session, authentification via JWT
                );// Pas de session HTTP, uniquement des tokens JWT

        // Ajouter le filtre JWT avant le filtre de gestion des authentifications
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();  // Construire l'objet SecurityFilterChain
    }
}
