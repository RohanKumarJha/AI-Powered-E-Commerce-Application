package com.ecommerce.service.strategy;

import java.util.Set;

public interface RecommendationStrategy {

    Set<Long> recommendProducts(Long userId);

}