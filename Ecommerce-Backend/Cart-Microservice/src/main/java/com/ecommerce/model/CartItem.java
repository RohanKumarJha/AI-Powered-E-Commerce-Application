package com.ecommerce.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem implements Serializable {

    private Long cartItemId;

    private Long productId;

    private String productNameSnapshot;

    private String skuSnapshot;

    private String mainImageUrlSnapshot;

    private BigDecimal priceSnapshot;

    @Builder.Default
    private BigDecimal discountSnapshot = BigDecimal.ZERO;

    private BigDecimal specialPriceSnapshot;

    private Integer quantity;

    private BigDecimal lineTotal;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}