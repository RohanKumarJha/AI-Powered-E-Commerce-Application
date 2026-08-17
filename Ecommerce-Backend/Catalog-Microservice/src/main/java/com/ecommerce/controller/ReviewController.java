package com.ecommerce.controller;

import com.ecommerce.dto.request.ReviewRequest;
import com.ecommerce.dto.response.ReviewResponse;
import com.ecommerce.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/products/{productId}")
    public ReviewResponse addReview(@PathVariable Long productId,
                                    @Valid @RequestBody ReviewRequest request) {
        log.info("Add review API called for product ID: {}", productId);
        return reviewService.addReview(productId, request);
    }

    @PutMapping("/{reviewId}")
    public ReviewResponse updateReview(@PathVariable Long reviewId,
                                       @Valid @RequestBody ReviewRequest request) {
        log.info("Update review API called for review ID: {}", reviewId);
        return reviewService.updateReview(reviewId, request);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        log.info("Delete review API called for review ID: {}", reviewId);
        reviewService.deleteReview(reviewId);
    }

    @GetMapping("/products/{productId}")
    public List<ReviewResponse> getProductReviews(@PathVariable Long productId) {
        log.info("Get product reviews API called for product ID: {}", productId);
        return reviewService.getProductReviews(productId);
    }
}