package com.ecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationPreferenceResponse {

    private Long preferenceId;

    private Long userId;

    private Boolean emailEnabled;

    private Boolean smsEnabled;

    private Boolean pushEnabled;

    private Boolean active;

    private Long createdBy;

    private Long updatedBy;

}