package com.ecommerce.dto.request;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {

    @NotBlank(message = "Category name is required.")
    @Size(min = 3, max = 100, message = "Category name must be between 3 and 100 characters.")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters.")
    private String description;

    @Positive(message = "Parent category ID must be greater than 0.")
    private Long parentCategoryId;

    @Size(max = 500, message = "Image URL cannot exceed 500 characters.")
    private String imageUrl;

    private Boolean active;
}