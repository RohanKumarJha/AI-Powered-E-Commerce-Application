package com.ecommerce.controller;

import com.ecommerce.dto.request.ChatRequest;
import com.ecommerce.dto.request.ReviewSummaryRequest;
import com.ecommerce.dto.response.ChatResponse;
import com.ecommerce.dto.response.RecommendationResponse;
import com.ecommerce.dto.response.ReviewSummaryResponse;
import com.ecommerce.service.AIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ai")
@Tag(
        name = "AI Controller",
        description = "APIs for product recommendations, AI chat and review summarization."
)
@RequiredArgsConstructor
@Slf4j
public class AIController {

    private final AIService aiService;

    @GetMapping("/recommendations/{userId}")
    @Operation(summary = "Get product recommendations")
    public ResponseEntity<RecommendationResponse> getRecommendations(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "RULE_BASED") String strategy) {
        log.info(
                "Received request to get recommendations for userId: {} using strategy: {}",
                userId,
                strategy
        );
        RecommendationResponse response =
                aiService.getRecommendations(userId, strategy);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/chat")
    @Operation(summary = "Chat with AI assistant")
    public ResponseEntity<ChatResponse> chat(
            @Valid
            @RequestBody
            ChatRequest request) {
        log.info(
                "Received AI chat request for userId: {}",
                request.getUserId()
        );
        ChatResponse response =
                aiService.chat(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reviews/summarize")
    @Operation(summary = "Summarize customer reviews")
    public ResponseEntity<ReviewSummaryResponse> summarizeReviews(
            @Valid
            @RequestBody
            ReviewSummaryRequest request) {
        log.info(
                "Received review summarization request with {} reviews.",
                request.getReviews().size()
        );
        ReviewSummaryResponse response =
                aiService.summarizeReviews(request);
        return ResponseEntity.ok(response);
    }

}