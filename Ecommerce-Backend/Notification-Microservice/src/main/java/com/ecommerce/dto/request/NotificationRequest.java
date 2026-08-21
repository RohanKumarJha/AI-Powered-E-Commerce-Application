package com.ecommerce.dto.request;

import com.ecommerce.model.enums.NotificationType;
import com.ecommerce.model.enums.ReferenceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {

    @NotNull(message = "User ID is required.")
    private Long userId;

    @NotNull(message = "Notification type is required.")
    private NotificationType notificationType;

    @NotBlank(message = "Recipient is required.")
    private String recipient;

    @NotBlank(message = "Subject is required.")
    private String subject;

    @NotBlank(message = "Message is required.")
    private String message;

    @NotNull(message = "Reference type is required.")
    private ReferenceType referenceType;

    @NotNull(message = "Reference ID is required.")
    private Long referenceId;

}