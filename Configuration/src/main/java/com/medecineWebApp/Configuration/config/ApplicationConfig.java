package com.medecineWebApp.Configuration.config;


import com.medecineWebApp.Configuration.auditing.ApplicationAuditAware;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

import static com.google.common.net.HttpHeaders.X_REQUESTED_WITH;
import static org.springframework.http.HttpHeaders.*;

@Configuration

public class ApplicationConfig {
    private final UserDetailsService userDetailsService;

    public ApplicationConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationManager authManager(@Lazy AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider AuthentProvider = new DaoAuthenticationProvider();
        AuthentProvider.setUserDetailsService(userDetailsService);
        AuthentProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return AuthentProvider;
    }

    @Bean
    public AuditorAware<Long> auditorAware() {
        return new ApplicationAuditAware();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("""
                http://localhost:4200"""));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH"));
        config.setAllowedHeaders(Arrays.asList(
                ORIGIN,
                AUTHORIZATION,
                CONTENT_TYPE,
                ACCEPT,
                "X-Requested-With",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers"

                ));
        config.setExposedHeaders(
                Arrays.asList(ORIGIN,
                        ACCESS_CONTROL_ALLOW_ORIGIN,
                        CONTENT_TYPE,
                        ACCEPT,
                        AUTHORIZATION,
                        X_REQUESTED_WITH,
                        ACCESS_CONTROL_REQUEST_METHOD,
                        ACCESS_CONTROL_REQUEST_HEADERS,
                        ACCESS_CONTROL_ALLOW_CREDENTIALS,
                        "Jwt-Token",
                        "File-Name")
                );
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
