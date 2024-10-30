package com.medecineWebApp.Configuration.repository;

import com.medecineWebApp.Configuration.models.setting.SmtpConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailConfigRepository extends JpaRepository<SmtpConfig, Long>  {
}
