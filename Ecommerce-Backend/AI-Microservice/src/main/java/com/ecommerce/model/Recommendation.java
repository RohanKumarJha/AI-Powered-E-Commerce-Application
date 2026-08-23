package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationId;

    @Column(nullable = false, unique = true)
    private Long userId;

    @ElementCollection
    @CollectionTable(
            name = "recommendation_products",
            joinColumns = @JoinColumn(name = "recommendation_id")
    )
    @Column(name = "product_id")
    @Builder.Default
    private Set<Long> recommendedProductIds = new HashSet<>();

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    private Long createdBy;

    private Long updatedBy;
}