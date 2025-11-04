package com.medecineWebApp.Configuration.config;


import com.medecineWebApp.Configuration.config.jwt.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private static final String[] WHITE_LIST_URL = {
            "/auth/**",
            "/user/**",
            "/files/**",
            "/fake/users",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",

    };

    private final JwtAuthFilter jwtAuthFilter;
    private final CustomLogoutHandler logoutHandler;
    private final AuthenticationProvider authenticationProvider;
    private final CustomPermissionEvaluator customPermissionEvaluator;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, CustomLogoutHandler logoutHandler, AuthenticationProvider authenticationProvider, CustomPermissionEvaluator customPermissionEvaluator) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.logoutHandler = logoutHandler;
        this.authenticationProvider = authenticationProvider;
        this.customPermissionEvaluator = customPermissionEvaluator;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   SessionValidationFilter sessionValidationFilter)
            throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL).permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
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
                                .requestMatchers("/api/leave_type/**").permitAll()
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
                                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(sessionValidationFilter, JwtAuthFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }

    @Bean
    public MethodSecurityExpressionHandler expressionHandler() {
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
        handler.setPermissionEvaluator(customPermissionEvaluator);  // Use custom permission evaluator
        return handler;
    }


}
