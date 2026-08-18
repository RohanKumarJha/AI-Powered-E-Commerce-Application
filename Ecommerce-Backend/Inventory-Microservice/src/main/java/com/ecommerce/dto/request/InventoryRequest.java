package com.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryRequest {

    @NotNull(message = "Product ID is required.")
    private Long productId;

    @NotBlank(message = "SKU is required.")
    private String sku;

    @NotNull(message = "Available quantity is required.")
    private Integer availableQuantity;

    @NotNull(message = "Minimum stock level is required.")
    private Integer minimumStockLevel;
}