package com.ecommerce.client.order.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long orderId;

    private String orderNumber;

    private Long userId;

    private Long addressId;

    private BigDecimal totalAmount;

    private String orderStatus;

    private String paymentMethod;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}