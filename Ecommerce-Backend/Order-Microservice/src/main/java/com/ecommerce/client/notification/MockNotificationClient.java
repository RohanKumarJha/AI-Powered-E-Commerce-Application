package com.ecommerce.client.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MockNotificationClient implements NotificationClient {

    @Override
    public void sendOrderPlacedNotification(Long userId, Long orderId) {
        log.info(
                "Sending mock order placed notification to userId: {} for orderId: {}",
                userId,
                orderId
        );
        log.debug(
                "Mock order placed notification sent successfully for orderId: {}",
                orderId
        );
    }
}