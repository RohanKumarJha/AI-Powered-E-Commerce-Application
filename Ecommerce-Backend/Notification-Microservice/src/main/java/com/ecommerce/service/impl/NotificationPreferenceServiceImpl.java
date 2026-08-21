package com.ecommerce.service.impl;

import com.ecommerce.dto.request.NotificationPreferenceRequest;
import com.ecommerce.dto.response.NotificationPreferenceResponse;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.NotificationPreferenceMapper;
import com.ecommerce.model.NotificationPreference;
import com.ecommerce.repository.NotificationPreferenceRepository;
import com.ecommerce.service.NotificationPreferenceService;
import com.ecommerce.service.factory.NotificationPreferenceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationPreferenceServiceImpl implements NotificationPreferenceService {

    private final NotificationPreferenceRepository preferenceRepository;
    private final NotificationPreferenceFactory preferenceFactory;
    private final NotificationPreferenceMapper preferenceMapper;

    @Override
    public NotificationPreferenceResponse createPreference(
            NotificationPreferenceRequest request) {
        log.info(
                "Creating notification preference for userId: {}",
                request.getUserId()
        );
        NotificationPreference preference =
                preferenceFactory.createPreference(request);
        NotificationPreference saved =
                preferenceRepository.save(preference);
        log.info(
                "Notification preference created successfully for userId: {}",
                saved.getUserId()
        );
        return preferenceMapper.toResponse(saved);
    }

    @Override
    public NotificationPreferenceResponse getPreferenceByUserId(
            Long userId) {
        log.info(
                "Fetching notification preference for userId: {}",
                userId
        );
        NotificationPreference preference =
                preferenceRepository.findByUserId(userId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Notification preference not found for user: "
                                        + userId
                        ));
        log.info(
                "Notification preference fetched successfully for userId: {}",
                userId
        );
        return preferenceMapper.toResponse(preference);
    }

    @Override
    public NotificationPreferenceResponse updatePreference(
            Long userId,
            NotificationPreferenceRequest request) {
        log.info(
                "Updating notification preference for userId: {}",
                userId
        );
        NotificationPreference preference =
                preferenceRepository.findByUserId(userId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Notification preference not found for user: "
                                        + userId
                        ));
        preference.setEmailEnabled(request.getEmailEnabled());
        preference.setSmsEnabled(request.getSmsEnabled());
        preference.setPushEnabled(request.getPushEnabled());
        preferenceFactory.updatePreference(preference);
        NotificationPreference updated =
                preferenceRepository.save(preference);
        log.info(
                "Notification preference updated successfully for userId: {}",
                userId
        );
        return preferenceMapper.toResponse(updated);
    }

    @Override
    public void deletePreference(
            Long userId) {
        log.info(
                "Deleting notification preference for userId: {}",
                userId
        );
        NotificationPreference preference =
                preferenceRepository.findByUserId(userId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Notification preference not found for user id: "
                                        + userId
                        ));
        if (!preference.getActive()) {
            throw new ResourceNotFoundException(
                    "Notification preference not found for user id: "
                            + userId
            );
        }
        preference.setActive(false);
        preferenceFactory.updatePreference(preference);
        preferenceRepository.save(preference);
        log.info(
                "Notification preference deleted successfully for userId: {}",
                userId
        );
    }
}