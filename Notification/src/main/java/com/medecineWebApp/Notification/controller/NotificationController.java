package com.medecineWebApp.Notification.controller;

import com.medecineWebApp.Notification.dto.NotificationDTO;
import com.medecineWebApp.Notification.filter.NotificationFilter;
import com.medecineWebApp.Notification.filter.NotificationSpecifications;
import com.medecineWebApp.Notification.models.Notification;
import com.medecineWebApp.Notification.repositories.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Notification>> searchNotifications(NotificationFilter filter) {
//        List<Notification> notifications = notificationRepository.findAll(NotificationSpecifications.withFilters(filter));
//        return ResponseEntity.ok(notifications);
//    }
    @GetMapping("/search")
    public ResponseEntity<Page<Notification>> searchNotifications(
            NotificationFilter filter,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Notification> notifications = notificationRepository.findAll(
                NotificationSpecifications.withFilters(filter),
                pageable
        );
        return ResponseEntity.ok(notifications);
    }
}
