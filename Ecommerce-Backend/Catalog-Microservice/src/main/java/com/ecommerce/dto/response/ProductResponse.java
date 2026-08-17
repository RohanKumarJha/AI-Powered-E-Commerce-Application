package com.ecommerce.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long productId;

    private String name;

    private String description;

    private String sku;

    private BigDecimal price;

    private BigDecimal discount;

    private BigDecimal specialPrice;

    private CategoryResponse category;

    private BrandResponse brand;

    private Long sellerId;

    private Double averageRating;

    private Integer reviewCount;

    private Boolean active;

    private Set<ProductImageResponse> images;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}