package com.ecommerce.model;

import com.ecommerce.model.enums.CartStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RedisHash("cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart implements Serializable {

    @Id
    private Long cartId;

    private Long userId;

    @Builder.Default
    private Set<CartItem> cartItems = new HashSet<>();

    @Builder.Default
    private Integer totalItems = 0;

    @Builder.Default
    private Integer totalQuantity = 0;

    @Builder.Default
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal totalDiscount = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal grandTotal = BigDecimal.ZERO;

    @Builder.Default
    private CartStatus cartStatus = CartStatus.ACTIVE;

    private LocalDateTime lastActivityAt;

    private LocalDateTime expiresAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}