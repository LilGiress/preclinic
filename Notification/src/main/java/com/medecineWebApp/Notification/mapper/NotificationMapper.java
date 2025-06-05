package com.medecineWebApp.Notification.mapper;

import com.medecineWebApp.Notification.dto.NotificationDTO;
import com.medecineWebApp.Notification.models.Notification;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
//    @Mapping(target = "createdBy", ignore = true)
//    @Mapping(target = "createdDate", ignore = true)
//    @Mapping(target = "lastModifiedBy", ignore = true)
//    @Mapping(target = "lastModifiedDate", ignore = true)
    NotificationDTO notificationToNotificationDTO(Notification notification);
    @InheritInverseConfiguration
    Notification notificationDTOToNotification(NotificationDTO notificationDTO);
}
