package com.ecommerce.service;

import com.ecommerce.dto.request.NotificationPreferenceRequest;
import com.ecommerce.dto.response.NotificationPreferenceResponse;

public interface NotificationPreferenceService {


    NotificationPreferenceResponse createPreference(
            NotificationPreferenceRequest request
    );


    NotificationPreferenceResponse getPreferenceByUserId(
            Long userId
    );


    NotificationPreferenceResponse updatePreference(
            Long userId,
            NotificationPreferenceRequest request
    );


    void deletePreference(
            Long userId
    );

}