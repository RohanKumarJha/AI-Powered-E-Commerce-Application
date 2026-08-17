package com.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandRequest {

    @NotBlank(message = "Brand name is required.")
    @Size(min = 2, max = 100, message = "Brand name must be between 2 and 100 characters.")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters.")
    private String description;

    @Size(max = 500, message = "Logo URL cannot exceed 500 characters.")
    private String logoUrl;

    @Pattern(
            regexp = "^(https?://.*)?$",
            message = "Website must be a valid HTTP or HTTPS URL."
    )
    private String website;

    private Boolean active;
}