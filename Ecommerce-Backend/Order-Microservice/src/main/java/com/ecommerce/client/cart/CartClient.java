package com.ecommerce.client.cart;

import com.ecommerce.client.cart.response.CartResponse;

public interface CartClient {

    CartResponse getCartByUserId(Long userId);
}