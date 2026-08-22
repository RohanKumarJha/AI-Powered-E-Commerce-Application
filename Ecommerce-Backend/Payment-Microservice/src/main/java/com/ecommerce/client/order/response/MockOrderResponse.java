package com.ecommerce.client.order.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MockOrderResponse {

    private MockOrderResponse() {
    }

    public static OrderResponse getOrder(Long orderId) {

        return OrderResponse.builder()
                .orderId(orderId)
                .orderNumber("ORD-10001")
                .userId(1L)
                .addressId(10L)
                .totalAmount(new BigDecimal("1599.00"))
                .orderStatus("CONFIRMED")
                .paymentMethod("UPI")
                .createdAt(LocalDateTime.now().minusHours(2))
                .updatedAt(LocalDateTime.now())
                .build();

    }

}