package com.ecommerce.service;

import com.ecommerce.dto.request.UpdateCartStatusRequest;
import com.ecommerce.dto.response.CartResponse;
import com.ecommerce.dto.response.PageResponse;

import java.util.List;

public interface CartService {

    CartResponse createCart();

    PageResponse<CartResponse> getAllCarts(
            Integer page,
            Integer size,
            String sortBy,
            String direction
    );

    CartResponse getCartById(Long cartId);

    CartResponse getCartByUserId(Long userId);

    CartResponse updateCartStatus(Long cartId, UpdateCartStatusRequest request);

    void deleteCart(Long cartId);
}