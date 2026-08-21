package com.ecommerce.service.impl;

import com.ecommerce.dto.request.NotificationRequest;
import com.ecommerce.dto.request.UpdateNotificationStatusRequest;
import com.ecommerce.dto.response.NotificationResponse;
import com.ecommerce.dto.response.PageResponse;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.NotificationMapper;
import com.ecommerce.model.Notification;
import com.ecommerce.model.enums.NotificationStatus;
import com.ecommerce.model.enums.NotificationType;
import com.ecommerce.repository.NotificationRepository;
import com.ecommerce.service.NotificationService;
import com.ecommerce.service.factory.NotificationFactory;
import com.ecommerce.service.strategy.NotificationStrategy;
import com.ecommerce.util.PageRequestUtil;
import com.ecommerce.util.PageResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationFactory notificationFactory;
    private final NotificationMapper notificationMapper;

    private final Map<NotificationType, NotificationStrategy> strategyMap;

    @Override
    public NotificationResponse createNotification(
            NotificationRequest request) {
        log.info(
                "Creating {} notification for userId: {}",
                request.getNotificationType(),
                request.getUserId()
        );
        Notification notification =
                notificationFactory.createNotification(request);
        NotificationStrategy strategy =
                strategyMap.get(notification.getNotificationType());
        if (strategy == null) {
            throw new BadRequestException(
                    "Unsupported notification type: "
                            + notification.getNotificationType()
            );
        }
        boolean sent = strategy.send(notification);
        notification.setStatus(
                sent
                        ? NotificationStatus.SENT
                        : NotificationStatus.FAILED
        );
        Notification saved =
                notificationRepository.save(notification);
        log.info(
                "Notification processed successfully. NotificationId: {}, Status: {}",
                saved.getNotificationId(),
                saved.getStatus()
        );
        return notificationMapper.toResponse(saved);
    }

    @Override
    public NotificationResponse getNotificationById(
            Long notificationId) {
        log.info(
                "Fetching notification with ID: {}",
                notificationId
        );
        Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found with id: "
                                                + notificationId
                                )
                        );
        log.info(
                "Notification fetched successfully. NotificationId: {}",
                notificationId
        );
        return notificationMapper.toResponse(notification);
    }

    @Override
    public PageResponse<NotificationResponse> getAllNotifications(
            int page,
            int size) {
        log.info(
                "Fetching all notifications. Page: {}, Size: {}",
                page,
                size
        );
        Page<Notification> notifications =
                notificationRepository.findAll(
                        PageRequestUtil.create(page, size)
                );
        log.info("Notifications fetched successfully.");
        return PageResponseUtil.convert(
                notifications,
                notificationMapper::toResponse
        );
    }

    @Override
    public PageResponse<NotificationResponse> getNotificationsByUserId(
            Long userId,
            int page,
            int size) {
        log.info(
                "Fetching notifications for userId: {}. Page: {}, Size: {}",
                userId,
                page,
                size
        );
        Page<Notification> notifications =
                notificationRepository.findByUserId(
                        userId,
                        PageRequestUtil.create(page, size)
                );
        log.info(
                "Notifications fetched successfully for userId: {}",
                userId
        );
        return PageResponseUtil.convert(
                notifications,
                notificationMapper::toResponse
        );
    }

    @Override
    public NotificationResponse updateNotificationStatus(
            Long notificationId,
            UpdateNotificationStatusRequest request) {
        log.info(
                "Updating notification status. NotificationId: {}, New Status: {}",
                notificationId,
                request.getStatus()
        );
        Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found with id: "
                                                + notificationId
                                )
                        );
        notification.setStatus(request.getStatus());
        notificationFactory.updateNotification(notification);
        Notification updated =
                notificationRepository.save(notification);
        log.info(
                "Notification status updated successfully. NotificationId: {}",
                notificationId
        );
        return notificationMapper.toResponse(updated);
    }

    @Override
    public void deleteNotification(
            Long notificationId) {
        log.info(
                "Deleting notification with ID: {}",
                notificationId
        );
        Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found with id: "
                                                + notificationId
                                )
                        );
        if (!notification.getActive()) {
            throw new ResourceNotFoundException(
                    "Notification not found with id: " + notificationId
            );
        }
        notification.setActive(false);
        notificationFactory.updateNotification(notification);
        notificationRepository.save(notification);
        log.info(
                "Notification deactivated successfully. NotificationId: {}",
                notificationId
        );
    }
}