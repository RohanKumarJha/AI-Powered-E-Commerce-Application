package com.ecommerce.dto.response;

import com.ecommerce.model.enums.OrderStatus;
import com.ecommerce.model.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long orderId;

    private Long userId;

    private Long cartId;

    private BigDecimal totalAmount;

    private OrderStatus orderStatus;

    private PaymentMethod paymentMethod;

    private List<OrderItemResponse> items;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}