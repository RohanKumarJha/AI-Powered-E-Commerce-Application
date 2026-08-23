package com.ecommerce.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationResponse {

    private Long recommendationId;

    private Long userId;

    private Set<Long> recommendedProductIds;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}