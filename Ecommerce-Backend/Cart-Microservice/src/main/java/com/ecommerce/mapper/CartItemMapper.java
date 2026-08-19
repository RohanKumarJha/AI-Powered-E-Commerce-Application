package com.ecommerce.mapper;

import com.ecommerce.dto.request.CartItemRequest;
import com.ecommerce.dto.response.CartItemResponse;
import com.ecommerce.model.CartItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemMapper {

    private final ModelMapper modelMapper;

    public CartItem toEntity(CartItemRequest request) {
        return modelMapper.map(request, CartItem.class);
    }

    public CartItemResponse toResponse(CartItem cartItem) {
        return modelMapper.map(cartItem, CartItemResponse.class);
    }
}