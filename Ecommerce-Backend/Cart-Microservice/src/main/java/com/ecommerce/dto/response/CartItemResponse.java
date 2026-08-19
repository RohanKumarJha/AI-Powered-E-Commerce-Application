package com.ecommerce.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponse {

    private Long cartItemId;

    private Long productId;

    private String productNameSnapshot;

    private String skuSnapshot;

    private String mainImageUrlSnapshot;

    private BigDecimal priceSnapshot;

    private BigDecimal discountSnapshot;

    private BigDecimal specialPriceSnapshot;

    private Integer quantity;

    private BigDecimal lineTotal;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}