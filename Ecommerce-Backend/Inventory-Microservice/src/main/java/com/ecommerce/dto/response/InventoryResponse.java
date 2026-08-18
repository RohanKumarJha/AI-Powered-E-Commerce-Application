package com.ecommerce.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {

    private Long inventoryId;

    private Long productId;

    private String sku;

    private Integer availableQuantity;

    private Integer reservedQuantity;

    private Integer soldQuantity;

    private Integer minimumStockLevel;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}