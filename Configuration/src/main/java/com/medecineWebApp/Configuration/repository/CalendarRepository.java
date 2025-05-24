package com.medecineWebApp.Configuration.repository;

import com.medecineWebApp.Configuration.models.setting.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Calendar findByUserId(Long userId);
}
