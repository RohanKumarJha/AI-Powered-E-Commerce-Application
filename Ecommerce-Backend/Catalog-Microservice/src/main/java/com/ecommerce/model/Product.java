package com.ecommerce.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    private Long productId;

    private String name;

    private String description;

    private String sku;

    private BigDecimal price;

    @Builder.Default
    private BigDecimal discount = BigDecimal.ZERO;

    private BigDecimal specialPrice;

    private Long categoryId;

    private Long brandId;

    private Long sellerId;

    @Builder.Default
    private Double averageRating = 0.0;

    @Builder.Default
    private Integer reviewCount = 0;

    @Builder.Default
    private Boolean active = true;

    @Builder.Default
    private Set<Long> imageIds = new HashSet<>();

    @Builder.Default
    private Set<Long> reviewIds = new HashSet<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long createdBy;

    private Long updatedBy;
}