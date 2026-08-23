package com.ecommerce.service.impl;

import com.ecommerce.dto.request.ChatRequest;
import com.ecommerce.dto.request.ReviewSummaryRequest;
import com.ecommerce.dto.response.ChatResponse;
import com.ecommerce.dto.response.RecommendationResponse;
import com.ecommerce.dto.response.ReviewSummaryResponse;
import com.ecommerce.mapper.RecommendationMapper;
import com.ecommerce.model.Recommendation;
import com.ecommerce.repository.RecommendationRepository;
import com.ecommerce.service.AIService;
import com.ecommerce.service.adapter.AIProviderAdapter;
import com.ecommerce.service.factory.RecommendationFactory;
import com.ecommerce.service.factory.RecommendationStrategyFactory;
import com.ecommerce.service.strategy.RecommendationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIServiceImpl implements AIService {

    private final RecommendationRepository recommendationRepository;
    private final RecommendationMapper recommendationMapper;
    private final RecommendationFactory recommendationFactory;
    private final RecommendationStrategyFactory strategyFactory;
    private final AIProviderAdapter aiProviderAdapter;

    @Override
    public RecommendationResponse getRecommendations(
            Long userId,
            String strategy) {
        log.info(
                "Generating recommendations for userId: {} using strategy: {}",
                userId,
                strategy
        );
        RecommendationStrategy recommendationStrategy =
                strategyFactory.getStrategy(strategy);
        Set<Long> productIds =
                recommendationStrategy.recommendProducts(userId);
        Recommendation recommendation =
                recommendationRepository
                        .findByUserIdAndActiveTrue(userId)
                        .map(existing ->
                                recommendationFactory.updateRecommendation(
                                        existing,
                                        productIds,
                                        userId
                                ))
                        .orElseGet(() ->
                                recommendationFactory.createRecommendation(
                                        userId,
                                        productIds,
                                        userId
                                ));
        recommendation =
                recommendationRepository.save(recommendation);
        log.info(
                "Recommendations generated successfully for userId: {}. Total recommended products: {}",
                userId,
                productIds.size()
        );
        return recommendationMapper.toResponse(recommendation);
    }

    @Override
    public ChatResponse chat(
            ChatRequest request) {
        log.info(
                "Processing AI chat request for userId: {}",
                request.getUserId()
        );
        String answer = aiProviderAdapter.chat(
                request.getUserId(),
                request.getMessage()
        );
        log.info(
                "AI chat response generated successfully for userId: {}",
                request.getUserId()
        );
        return ChatResponse.builder()
                .userId(request.getUserId())
                .question(request.getMessage())
                .answer(answer)
                .build();
    }

    @Override
    public ReviewSummaryResponse summarizeReviews(
            ReviewSummaryRequest request) {
        log.info(
                "Generating summary for {} review(s).",
                request.getReviews().size()
        );
        String summary =
                aiProviderAdapter.summarizeReviews(
                        request.getReviews()
                );
        log.info("Review summary generated successfully.");
        return ReviewSummaryResponse.builder()
                .summary(summary)
                .build();
    }

}