package com.ecommerce.service.factory;

import com.ecommerce.dto.request.NotificationPreferenceRequest;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.mapper.NotificationPreferenceMapper;
import com.ecommerce.model.NotificationPreference;
import com.ecommerce.repository.NotificationPreferenceRepository;
import com.ecommerce.security.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationPreferenceFactory {

    private final NotificationPreferenceMapper preferenceMapper;
    private final NotificationPreferenceRepository preferenceRepository;

    public NotificationPreference createPreference(
            NotificationPreferenceRequest request) {
        log.debug(
                "Creating notification preference for userId: {}",
                request.getUserId()
        );
        if (preferenceRepository.existsByUserId(request.getUserId())) {
            throw new BadRequestException(
                    "Notification preference already exists for this user."
            );
        }
        NotificationPreference preference =
                preferenceMapper.toEntity(request);
        preference.setActive(true);
        preference.setCreatedBy(UserContext.getCurrentUserId());
        preference.setUpdatedBy(UserContext.getCurrentUserId());
        return preference;
    }

    public void updatePreference(NotificationPreference preference) {
        log.debug(
                "Updating notification preference for userId: {}",
                preference.getUserId()
        );
        preference.setUpdatedBy(UserContext.getCurrentUserId());
    }
}