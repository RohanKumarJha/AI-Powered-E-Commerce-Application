package com.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageRequest {

    @NotBlank(message = "Image URL is required.")
    @Size(max = 500, message = "Image URL cannot exceed 500 characters.")
    private String imageUrl;

    @PositiveOrZero(message = "Display order cannot be negative.")
    private Integer displayOrder;

    private Boolean primaryImage;
}