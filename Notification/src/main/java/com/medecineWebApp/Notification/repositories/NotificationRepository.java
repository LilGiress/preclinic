package com.medecineWebApp.Notification.repositories;

import com.medecineWebApp.Notification.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserName(String userName);
}
