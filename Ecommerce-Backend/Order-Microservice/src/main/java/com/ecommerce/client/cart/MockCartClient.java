package com.ecommerce.client.cart;

import com.ecommerce.client.cart.response.CartItemResponse;
import com.ecommerce.client.cart.response.CartResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MockCartClient implements CartClient {

    @Override
    public CartResponse getCartByUserId(Long userId) {
        log.info("Fetching mock cart for userId: {}", userId);
        CartItemResponse item1 = CartItemResponse.builder()
                .cartItemId(1L)
                .productId(1L)
                .quantity(2)
                .build();
        CartItemResponse item2 = CartItemResponse.builder()
                .cartItemId(2L)
                .productId(2L)
                .quantity(1)
                .build();
        CartResponse response = CartResponse.builder()
                .cartId(1L)
                .userId(userId)
                .items(List.of(item1, item2))
                .build();
        log.debug("Returning mock cart with {} items for userId: {}",
                response.getItems().size(),
                userId);
        return response;
    }
}