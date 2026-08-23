package com.ecommerce.service.factory;

import com.ecommerce.model.Recommendation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class RecommendationFactory {

    public Recommendation createRecommendation(
            Long userId,
            Set<Long> recommendedProductIds,
            Long createdBy) {
        return Recommendation.builder()
                .userId(userId)
                .recommendedProductIds(new HashSet<>(recommendedProductIds))
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .createdBy(createdBy)
                .updatedBy(createdBy)
                .build();
    }

    public Recommendation updateRecommendation(
            Recommendation recommendation,
            Set<Long> recommendedProductIds,
            Long updatedBy) {
        recommendation.setRecommendedProductIds(
                new HashSet<>(recommendedProductIds)
        );
        recommendation.setUpdatedAt(LocalDateTime.now());
        recommendation.setUpdatedBy(updatedBy);
        return recommendation;
    }

}