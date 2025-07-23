package com.medecineWebApp.Configuration.config;

import com.medecineWebApp.Configuration.repository.user.UserSessionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomLogoutHandler implements LogoutHandler {
    private final UserSessionRepository  userSessionRepository;

    public CustomLogoutHandler(UserSessionRepository userSessionRepository) {
        this.userSessionRepository = userSessionRepository;
    }

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Long sessionId = Long.valueOf(session.getId());
            session.invalidate();

            userSessionRepository.findById(String.valueOf(sessionId)).ifPresent(s -> {
                s.setActive(false);
                s.setLastAccessedAt(LocalDateTime.now());
                userSessionRepository.save(s);
            });
        }
        SecurityContextHolder.clearContext();

    }
}
