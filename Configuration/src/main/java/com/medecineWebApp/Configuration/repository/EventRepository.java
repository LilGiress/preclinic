package com.medecineWebApp.Configuration.repository;

import com.medecineWebApp.Configuration.models.setting.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
