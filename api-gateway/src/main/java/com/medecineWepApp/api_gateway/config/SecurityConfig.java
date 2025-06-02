package com.medecineWepApp.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    private static final String[] WHITE_LIST_URL = {
            "/auth/**",
            "/user/**",
            "/files/**",
            "/api/roles/**",
            "/api/departement/**",
            "/api/permission/**",
            "/api/addresses/**",
            "/api/cities/**",
            "/api/services/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",

    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL).permitAll()
                               /* .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/user/**").permitAll()
                                .requestMatchers("/api/files/**").permitAll()
                                .requestMatchers("/api/regions/**").permitAll()
                                .requestMatchers("/api/countries/**").permitAll()
                                .requestMatchers("/api/cities/**").permitAll()
                                .requestMatchers("/api/addresses/**").permitAll()
                                .requestMatchers("/api/departement/**").permitAll()
                                .requestMatchers("/api/services/**").permitAll()
                                .requestMatchers("/api/roles/**").permitAll()
                                .requestMatchers("/api/theme/**").permitAll()
                                .requestMatchers("/api/events/**").permitAll()
                                .requestMatchers("/api/email-config/**").permitAll()
                                .requestMatchers("/api/calendars/**").permitAll()
                                .requestMatchers("/api/leaves/**").permitAll()
                                .requestMatchers("/api/leavetype/**").permitAll()
                                .requestMatchers("/api/files/profile/**").permitAll()
                                .requestMatchers("/api/holidays/**").permitAll()
                                .requestMatchers("/api/medicalfiles/**").permitAll()
                                .requestMatchers("/api/permission/**").permitAll()


                                /*.requestMatchers("/api/files/upload/doctor/**").hasRole("MEDECIN")
                                .requestMatchers("/api/files/upload/management/**").hasRole("ADMIN")
                                .requestMatchers("/api/files/list/doctor/**").hasAnyRole("MEDECIN", "ADMIN")
                                .requestMatchers("/api/files/list/management/**").hasRole("ADMIN")
                                 .requestMatchers("/api/files/profile/upload/**").hasAnyRole("MEDECIN", "EMPLOYE", "PATIENT")
                                 .requestMatchers("/api/folders/create/**").hasAnyRole("PATIENT", "MEDECIN", "EMPLOYE", "ADMIN")
                                  .requestMatchers("/api/folders/create/**", "/api/folders/delete/**").hasAnyRole("PATIENT", "MEDECIN", "EMPLOYE", "ADMIN")
                                  .requestMatchers("/api/folders/list/**")
                                 .requestMatchers("/api/files/profile/**").permitAll()
                                */
                           /*     .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                .anyRequest()
                                .authenticated()

        );
        return http.build();
    }
}
*/