package com.ecommerce.service;

import com.ecommerce.dto.request.CartItemRequest;
import com.ecommerce.dto.request.UpdateCartItemQuantityRequest;
import com.ecommerce.dto.response.CartItemResponse;

import java.util.List;

public interface CartItemService {

    CartItemResponse addItem(CartItemRequest request);

    List<CartItemResponse> getItemsByCart(Long cartId);

    CartItemResponse getItemById(Long cartItemId);

    CartItemResponse updateQuantity(Long cartItemId, UpdateCartItemQuantityRequest request);

    void removeItem(Long cartItemId);
}