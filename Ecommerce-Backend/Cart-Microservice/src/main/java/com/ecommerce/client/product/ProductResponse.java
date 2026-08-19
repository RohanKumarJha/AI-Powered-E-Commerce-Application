package com.ecommerce.client.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long productId;
    private String name;
    private String sku;
    private String mainImageUrl;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal specialPrice;
}