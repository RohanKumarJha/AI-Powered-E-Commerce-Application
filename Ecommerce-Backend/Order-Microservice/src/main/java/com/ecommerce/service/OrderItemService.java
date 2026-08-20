package com.ecommerce.service;

import com.ecommerce.dto.request.OrderItemRequest;
import com.ecommerce.dto.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {

    OrderItemResponse addOrderItem(OrderItemRequest request);

    List<OrderItemResponse> getItemsByOrderId(Long orderId);

    OrderItemResponse getOrderItemById(Long orderItemId);

    void deleteOrderItem(Long orderItemId);
}