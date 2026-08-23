package com.ecommerce.service.factory;

import com.ecommerce.exception.BadRequestException;
import com.ecommerce.service.strategy.RecommendationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class RecommendationStrategyFactory {

    private final Map<String, RecommendationStrategy> strategies;

    public RecommendationStrategy getStrategy(
            String strategy) {
        log.debug(
                "Selecting recommendation strategy: {}",
                strategy
        );
        RecommendationStrategy recommendationStrategy =
                strategies.get(strategy.toUpperCase());
        if (recommendationStrategy == null) {
            throw new BadRequestException(
                    "Unsupported recommendation strategy: " + strategy
            );
        }
        log.debug(
                "Recommendation strategy selected successfully: {}",
                strategy
        );
        return recommendationStrategy;
    }

}