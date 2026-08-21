package com.ecommerce.controller;

import com.ecommerce.dto.request.NotificationPreferenceRequest;
import com.ecommerce.dto.response.NotificationPreferenceResponse;
import com.ecommerce.service.NotificationPreferenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notification-preferences")
@RequiredArgsConstructor
@Slf4j
public class NotificationPreferenceController {

    private final NotificationPreferenceService preferenceService;

    @PostMapping
    public ResponseEntity<NotificationPreferenceResponse> createPreference(
            @Valid @RequestBody NotificationPreferenceRequest request) {
        log.info(
                "Received request to create notification preference for userId: {}",
                request.getUserId()
        );
        NotificationPreferenceResponse response =
                preferenceService.createPreference(request);
        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<NotificationPreferenceResponse> getPreferenceByUserId(
            @PathVariable Long userId) {
        log.info(
                "Received request to fetch notification preference for userId: {}",
                userId
        );
        NotificationPreferenceResponse response =
                preferenceService.getPreferenceByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<NotificationPreferenceResponse> updatePreference(
            @PathVariable Long userId,
            @Valid @RequestBody NotificationPreferenceRequest request) {
        log.info(
                "Received request to update notification preference for userId: {}",
                userId
        );
        NotificationPreferenceResponse response =
                preferenceService.updatePreference(
                        userId,
                        request
                );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletePreference(
            @PathVariable Long userId) {
        log.info(
                "Received request to delete notification preference for userId: {}",
                userId
        );
        preferenceService.deletePreference(userId);
        return ResponseEntity.noContent().build();
    }
}