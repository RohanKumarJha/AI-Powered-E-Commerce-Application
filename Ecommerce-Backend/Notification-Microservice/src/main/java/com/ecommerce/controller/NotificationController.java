package com.ecommerce.controller;

import com.ecommerce.dto.request.NotificationRequest;
import com.ecommerce.dto.request.UpdateNotificationStatusRequest;
import com.ecommerce.dto.response.NotificationResponse;
import com.ecommerce.dto.response.PageResponse;
import com.ecommerce.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(
            @Valid @RequestBody NotificationRequest request) {
        log.info("Received request to create notification for userId: {}", request.getUserId());
        NotificationResponse response =
                notificationService.createNotification(request);
        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResponse> getNotificationById(
            @PathVariable Long notificationId) {
        log.info("Received request to fetch notification with ID: {}", notificationId);
        NotificationResponse response =
                notificationService.getNotificationById(notificationId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<NotificationResponse>> getAllNotifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Received request to fetch all notifications. Page: {}, Size: {}", page, size);
        PageResponse<NotificationResponse> response =
                notificationService.getAllNotifications(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PageResponse<NotificationResponse>> getNotificationsByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info(
                "Received request to fetch notifications for userId: {}. Page: {}, Size: {}",
                userId,
                page,
                size
        );
        PageResponse<NotificationResponse> response =
                notificationService.getNotificationsByUserId(
                        userId,
                        page,
                        size
                );
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{notificationId}/status")
    public ResponseEntity<NotificationResponse> updateNotificationStatus(
            @PathVariable Long notificationId,
            @Valid @RequestBody UpdateNotificationStatusRequest request) {
        log.info(
                "Received request to update status of notification ID: {}",
                notificationId
        );
        NotificationResponse response =
                notificationService.updateNotificationStatus(
                        notificationId,
                        request
                );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(
            @PathVariable Long notificationId) {
        log.info("Received request to delete notification with ID: {}", notificationId);
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.noContent().build();
    }
}