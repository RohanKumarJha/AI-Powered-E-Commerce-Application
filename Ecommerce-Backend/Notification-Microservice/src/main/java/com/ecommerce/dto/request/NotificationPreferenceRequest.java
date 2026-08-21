package com.ecommerce.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationPreferenceRequest {

    @NotNull(message = "User ID is required.")
    private Long userId;

    @NotNull(message = "Email preference is required.")
    private Boolean emailEnabled;

    @NotNull(message = "SMS preference is required.")
    private Boolean smsEnabled;

    @NotNull(message = "Push preference is required.")
    private Boolean pushEnabled;

}