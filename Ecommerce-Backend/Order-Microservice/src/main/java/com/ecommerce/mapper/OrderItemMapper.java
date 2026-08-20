package com.ecommerce.mapper;

import com.ecommerce.dto.request.OrderItemRequest;
import com.ecommerce.dto.response.OrderItemResponse;
import com.ecommerce.model.OrderItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {

    private final ModelMapper modelMapper;


    public OrderItem toEntity(OrderItemRequest request) {
        return modelMapper.map(request, OrderItem.class);
    }


    public OrderItemResponse toResponse(OrderItem orderItem) {
        return modelMapper.map(orderItem, OrderItemResponse.class);
    }
}