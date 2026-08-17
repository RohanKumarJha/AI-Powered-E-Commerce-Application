package com.ecommerce.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductFilterRequest {

    private String keyword;

    @Positive(message = "Category ID must be greater than 0.")
    private Long categoryId;

    @Positive(message = "Brand ID must be greater than 0.")
    private Long brandId;

    @DecimalMin(value = "0.0", message = "Minimum price cannot be negative.")
    private BigDecimal minPrice;

    @DecimalMin(value = "0.0", message = "Maximum price cannot be negative.")
    private BigDecimal maxPrice;

    @Min(value = 1, message = "Minimum rating must be at least 1.")
    @Max(value = 5, message = "Minimum rating cannot exceed 5.")
    private Double minimumRating;

    private Boolean active;
}