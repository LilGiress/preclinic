package com.medecineWebApp.Configuration.config;

import com.medecineWebApp.Configuration.models.user.Users;
import com.medecineWebApp.Configuration.repository.user.UserSessionRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SessionValidationFilter extends OncePerRequestFilter {
    private final UserSessionRepository userSessionRepository;

    public SessionValidationFilter(UserSessionRepository userSessionRepository) {
        this.userSessionRepository = userSessionRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            Object principal = auth.getPrincipal();

            if (principal instanceof Users user){

                String fingerprint = request.getHeader("Fingerprint");

                if (fingerprint == null || fingerprint.isBlank()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"error\": \"Fingerprint manquant\"}");
                    return;
                }

                boolean sessionActive = userSessionRepository
                        .findByUserAndFingerprintAndActiveTrue(user, fingerprint)
                        .isPresent();

                if (!sessionActive) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"error\": \"Session désactivée\"}");
                    return;
                }
            }


        }

        filterChain.doFilter(request, response);
    }
}
