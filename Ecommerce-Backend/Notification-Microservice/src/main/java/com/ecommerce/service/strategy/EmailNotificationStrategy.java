package com.ecommerce.service.strategy;

import com.ecommerce.model.Notification;
import com.ecommerce.service.adapter.NotificationProviderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailNotificationStrategy implements NotificationStrategy {

    private final NotificationProviderAdapter notificationProviderAdapter;

    @Override
    public boolean send(Notification notification) {
        return notificationProviderAdapter.sendEmail(
                notification.getRecipient(),
                notification.getSubject(),
                notification.getMessage()
        );
    }

}