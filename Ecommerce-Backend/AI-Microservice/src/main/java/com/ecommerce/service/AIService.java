package com.ecommerce.service;

import com.ecommerce.dto.request.ChatRequest;
import com.ecommerce.dto.request.ReviewSummaryRequest;
import com.ecommerce.dto.response.ChatResponse;
import com.ecommerce.dto.response.RecommendationResponse;
import com.ecommerce.dto.response.ReviewSummaryResponse;

public interface AIService {

    RecommendationResponse getRecommendations(
            Long userId,
            String strategy
    );

    ChatResponse chat(ChatRequest request);

    ReviewSummaryResponse summarizeReviews(
            ReviewSummaryRequest request
    );

}