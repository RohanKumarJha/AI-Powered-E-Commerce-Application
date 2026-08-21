package com.ecommerce.mapper;

import com.ecommerce.dto.request.NotificationRequest;
import com.ecommerce.dto.response.NotificationResponse;
import com.ecommerce.model.Notification;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationMapper {

    private final ModelMapper modelMapper;


    public Notification toEntity(NotificationRequest request) {

        return modelMapper.map(request, Notification.class);
    }


    public NotificationResponse toResponse(Notification notification) {

        return modelMapper.map(notification, NotificationResponse.class);
    }

}