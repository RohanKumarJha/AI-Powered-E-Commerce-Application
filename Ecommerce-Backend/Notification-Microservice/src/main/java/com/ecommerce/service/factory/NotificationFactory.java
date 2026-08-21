package com.ecommerce.service.factory;

import com.ecommerce.dto.request.NotificationRequest;
import com.ecommerce.mapper.NotificationMapper;
import com.ecommerce.model.Notification;
import com.ecommerce.model.enums.NotificationStatus;
import com.ecommerce.security.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationFactory {

    private final NotificationMapper notificationMapper;

    public Notification createNotification(NotificationRequest request) {
        Notification notification = notificationMapper.toEntity(request);
        notification.setStatus(NotificationStatus.PENDING);
        notification.setActive(true);
        notification.setCreatedBy(UserContext.getCurrentUserId());
        notification.setUpdatedBy(UserContext.getCurrentUserId());
        return notification;
    }


    public void updateNotification(Notification notification) {
        notification.setUpdatedBy(UserContext.getCurrentUserId());
    }

}