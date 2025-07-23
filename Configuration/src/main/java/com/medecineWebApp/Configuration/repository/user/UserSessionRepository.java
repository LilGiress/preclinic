package com.medecineWebApp.Configuration.repository.user;

import com.medecineWebApp.Configuration.models.user.UserSession;
import com.medecineWebApp.Configuration.models.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSession, String> {
    List<UserSession>  findByUserAndActiveTrue(Users user);

    Optional<UserSession> findByUserAndFingerprintAndActiveTrue(Users user, String fingerprint);
}
