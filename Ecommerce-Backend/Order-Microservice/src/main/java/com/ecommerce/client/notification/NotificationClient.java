package com.ecommerce.client.notification;

public interface NotificationClient {

    void sendOrderPlacedNotification(Long userId, Long orderId);

}