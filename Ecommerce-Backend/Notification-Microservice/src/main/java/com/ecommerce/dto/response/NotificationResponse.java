package com.ecommerce.dto.response;

import com.ecommerce.model.enums.NotificationStatus;
import com.ecommerce.model.enums.NotificationType;
import com.ecommerce.model.enums.ReferenceType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {

    private Long notificationId;

    private Long userId;

    private NotificationType notificationType;

    private String recipient;

    private String subject;

    private String message;

    private NotificationStatus status;

    private ReferenceType referenceType;

    private Long referenceId;

    private Boolean active;

    private Long createdBy;

    private Long updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}