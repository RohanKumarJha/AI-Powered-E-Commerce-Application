package com.ecommerce.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {

    private Long brandId;

    private String name;

    private String description;

    private String logoUrl;

    private String website;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}