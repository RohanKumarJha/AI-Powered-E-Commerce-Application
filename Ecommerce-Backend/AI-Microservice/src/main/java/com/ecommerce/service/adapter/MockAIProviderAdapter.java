package com.ecommerce.service.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MockAIProviderAdapter implements AIProviderAdapter {

    @Override
    public String chat(
            Long userId,
            String message) {
        log.info(
                "Processing mock AI chat request for userId: {}",
                userId
        );
        String response =
                "Mock AI Response: I understand your query - \"" +
                        message +
                        "\". This feature will be integrated with a real AI provider later.";
        log.info(
                "Mock AI chat response generated successfully for userId: {}",
                userId
        );
        return response;
    }

    @Override
    public String summarizeReviews(
            List<String> reviews) {
        log.info(
                "Processing mock AI review summarization for {} review(s).",
                reviews.size()
        );
        String summary =
                "Summary: Based on " +
                        reviews.size() +
                        " review(s), customers are generally satisfied with the product.";
        log.info("Mock AI review summary generated successfully.");
        return summary;
    }

}