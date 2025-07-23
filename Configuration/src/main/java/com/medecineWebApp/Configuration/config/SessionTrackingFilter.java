package com.medecineWebApp.Configuration.config;

import com.medecineWebApp.Configuration.models.user.UserSession;
import com.medecineWebApp.Configuration.models.user.Users;
import com.medecineWebApp.Configuration.repository.user.UserRepository;
import com.medecineWebApp.Configuration.repository.user.UserSessionRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class SessionTrackingFilter extends OncePerRequestFilter {
    private final UserSessionRepository userSessionRepository;
    private  final UserRepository  userRepository;

    public SessionTrackingFilter(UserSessionRepository userSessionRepository, UserRepository userRepository) {
        this.userSessionRepository = userSessionRepository;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.isNew()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {

                Users user = (Users) auth.getPrincipal(); // ton UserDetails

                String userAgent = request.getHeader("User-Agent");
                String os = extractOS(userAgent);
                String ip = request.getRemoteAddr();

                UserSession userSession = new UserSession();
                userSession.setSessionId(session.getId());
                userSession.setUser(user);
                userSession.setUserAgent(detectBrowser(userAgent));
                userSession.setOs(os);
                userSession.setIpAddress(ip);
                userSession.setCreatedAt(LocalDateTime.now());
                userSession.setLastAccessedAt(LocalDateTime.now());
                userSession.setActive(true);

                userSessionRepository.save(userSession);
            }
        }

        filterChain.doFilter(request, response);

    }

    private String extractOS(String userAgent) {
        if (userAgent == null) return "Unknown";
        if (userAgent.contains("Windows")) return "Windows";
        if (userAgent.contains("Mac")) return "MacOS";
        if (userAgent.contains("X11")) return "Unix";
        if (userAgent.contains("Android")) return "Android";
        if (userAgent.contains("iPhone")) return "iOS";
        return "Other";
    }

    private String detectBrowser(String userAgent) {
        if (userAgent == null) return "Unknown";
        if (userAgent.contains("Chrome")) return "Chrome";
        if (userAgent.contains("Firefox")) return "Firefox";
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) return "Internet Explorer";
        if (userAgent.contains("Safari") && !userAgent.contains("Chrome")) return "Safari";
        if (userAgent.contains("Opera") || userAgent.contains("OPR")) return "Opera";
        return "Other";
    }
}
