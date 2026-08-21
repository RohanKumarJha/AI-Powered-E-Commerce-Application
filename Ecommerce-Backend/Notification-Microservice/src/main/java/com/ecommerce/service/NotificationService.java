package com.ecommerce.service;

import com.ecommerce.dto.request.NotificationRequest;
import com.ecommerce.dto.request.UpdateNotificationStatusRequest;
import com.ecommerce.dto.response.NotificationResponse;
import com.ecommerce.dto.response.PageResponse;

public interface NotificationService {


    NotificationResponse createNotification(
            NotificationRequest request
    );


    NotificationResponse getNotificationById(
            Long notificationId
    );


    PageResponse<NotificationResponse> getAllNotifications(
            int page,
            int size
    );


    PageResponse<NotificationResponse> getNotificationsByUserId(
            Long userId,
            int page,
            int size
    );


    NotificationResponse updateNotificationStatus(
            Long notificationId,
            UpdateNotificationStatusRequest request
    );


    void deleteNotification(
            Long notificationId
    );

}