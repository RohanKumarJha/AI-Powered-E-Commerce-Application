package com.ecommerce.service;

import com.ecommerce.dto.request.OrderRequest;
import com.ecommerce.dto.request.UpdateOrderStatusRequest;
import com.ecommerce.dto.response.OrderResponse;
import com.ecommerce.dto.response.PageResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    PageResponse<OrderResponse> getAllOrders(
            Integer page,
            Integer size,
            String sortBy,
            String sortDir
    );

    OrderResponse getOrderById(Long orderId);

    PageResponse<OrderResponse> getOrdersByUserId(
            Long userId,
            Integer page,
            Integer size,
            String sortBy,
            String sortDir
    );

    OrderResponse updateOrderStatus(
            Long orderId,
            UpdateOrderStatusRequest request
    );

    void deleteOrder(Long orderId);
}