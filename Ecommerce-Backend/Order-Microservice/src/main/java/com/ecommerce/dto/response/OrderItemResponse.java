package com.ecommerce.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponse {

    private Long orderItemId;

    private Long productId;

    private Integer quantity;

    private BigDecimal price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}