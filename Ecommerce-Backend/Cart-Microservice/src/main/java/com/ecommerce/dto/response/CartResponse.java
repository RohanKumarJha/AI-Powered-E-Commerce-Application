package com.ecommerce.dto.response;

import com.ecommerce.model.enums.CartStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {

    private Long cartId;

    private Long userId;

    private Integer totalItems;

    private Integer totalQuantity;

    private BigDecimal totalAmount;

    private BigDecimal totalDiscount;

    private BigDecimal grandTotal;

    private CartStatus cartStatus;

    private List<CartItemResponse> cartItems;

    private LocalDateTime lastActivityAt;

    private LocalDateTime expiresAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}