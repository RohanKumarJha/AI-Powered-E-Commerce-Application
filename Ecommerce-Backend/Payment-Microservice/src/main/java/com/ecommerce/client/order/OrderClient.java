package com.ecommerce.client.order;

import com.ecommerce.client.order.response.OrderResponse;

public interface OrderClient {

    OrderResponse getOrderById(Long orderId);

}