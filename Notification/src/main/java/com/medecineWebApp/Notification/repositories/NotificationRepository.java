package com.medecineWebApp.Notification.repositories;

import com.medecineWebApp.Notification.models.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>,JpaSpecificationExecutor<Notification> {
    Page<Notification> findByUserName(String userName);
}
