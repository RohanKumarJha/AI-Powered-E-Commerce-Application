package com.ecommerce.mapper;

import com.ecommerce.dto.request.NotificationPreferenceRequest;
import com.ecommerce.dto.response.NotificationPreferenceResponse;
import com.ecommerce.model.NotificationPreference;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationPreferenceMapper {

    private final ModelMapper modelMapper;


    public NotificationPreference toEntity(NotificationPreferenceRequest request) {

        return modelMapper.map(request, NotificationPreference.class);
    }


    public NotificationPreferenceResponse toResponse(NotificationPreference preference) {

        return modelMapper.map(preference, NotificationPreferenceResponse.class);
    }

}