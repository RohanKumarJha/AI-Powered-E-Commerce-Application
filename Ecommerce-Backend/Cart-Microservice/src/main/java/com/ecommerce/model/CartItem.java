package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    // Reference to Catalog Service
    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String productNameSnapshot;

    @Column(nullable = false)
    private String skuSnapshot;

    private String mainImageUrlSnapshot;

    @Column(nullable = false)
    private BigDecimal priceSnapshot;

    @Column(nullable = false)
    @Builder.Default
    private BigDecimal discountSnapshot = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal specialPriceSnapshot;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal lineTotal;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        lineTotal = specialPriceSnapshot.multiply(BigDecimal.valueOf(quantity));
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
        lineTotal = specialPriceSnapshot.multiply(BigDecimal.valueOf(quantity));
    }
}
