package com.ecommerce.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequest {

    @NotNull(message = "User ID is required.")
    @Positive(message = "User ID must be greater than 0.")
    private Long userId;

    @NotNull(message = "Rating is required.")
    @Min(value = 1, message = "Rating must be at least 1.")
    @Max(value = 5, message = "Rating cannot exceed 5.")
    private Integer rating;

    @NotBlank(message = "Review title is required.")
    @Size(min = 5, max = 150, message = "Review title must be between 5 and 150 characters.")
    private String title;

    @Size(max = 2000, message = "Review comment cannot exceed 2000 characters.")
    private String comment;
}