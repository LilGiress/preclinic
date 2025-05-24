package com.medecineWebApp.Configuration.repository.user;

import com.medecineWebApp.Configuration.models.user.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByEmailAndVerificationCode(String email, String verificationCode);
    void deleteByEmail(String email);
}
