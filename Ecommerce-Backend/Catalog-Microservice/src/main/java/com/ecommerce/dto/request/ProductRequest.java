package com.ecommerce.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for creating or updating a product")
public class ProductRequest {

    @NotBlank(message = "Product name is required.")
    @Size(min = 3, max = 200, message = "Product name must be between 3 and 200 characters.")
    private String name;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters.")
    private String description;

    @NotBlank(message = "SKU is required.")
    @Size(min = 3, max = 100, message = "SKU must be between 3 and 100 characters.")
    private String sku;

    @NotNull(message = "Product price is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than 0.")
    private BigDecimal price;

    @NotNull(message = "Discount is required.")
    @DecimalMin(value = "0.0", message = "Discount cannot be negative.")
    private BigDecimal discount;

    @NotNull(message = "Category ID is required.")
    @Positive(message = "Category ID must be greater than 0.")
    private Long categoryId;

    @NotNull(message = "Brand ID is required.")
    @Positive(message = "Brand ID must be greater than 0.")
    private Long brandId;

    private Boolean active;
}