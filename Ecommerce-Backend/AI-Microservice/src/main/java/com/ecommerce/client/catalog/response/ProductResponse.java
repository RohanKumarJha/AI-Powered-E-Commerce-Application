package com.ecommerce.client.catalog.response;

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

    private String category;

    private String brand;

    private BigDecimal price;

    private Double rating;

    private Boolean active;

}