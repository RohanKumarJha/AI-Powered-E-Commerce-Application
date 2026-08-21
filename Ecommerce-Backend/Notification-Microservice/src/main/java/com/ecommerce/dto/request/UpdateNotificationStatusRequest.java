package com.ecommerce.dto.request;

import com.ecommerce.model.enums.NotificationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateNotificationStatusRequest {

    @NotNull(message = "Notification status is required.")
    private NotificationStatus status;

}