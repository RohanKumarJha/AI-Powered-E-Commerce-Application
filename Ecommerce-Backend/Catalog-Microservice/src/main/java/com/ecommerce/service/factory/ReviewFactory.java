package com.ecommerce.service.factory;

import com.ecommerce.dto.request.ReviewRequest;
import com.ecommerce.exception.ResourceAlreadyExistsException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.ReviewMapper;
import com.ecommerce.model.Product;
import com.ecommerce.model.Review;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReviewFactory {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ReviewMapper reviewMapper;

    public Review create(ReviewRequest request) {
        log.debug("Creating review entity for user ID: {}", request.getUserId());
        Review review = reviewMapper.toEntity(request);
        log.debug("Review entity created successfully.");
        return review;
    }

    public Product getProduct(Long productId) {
        log.debug("Fetching product with ID: {}", productId);
        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "id", productId));
    }

    public Review getReview(Long reviewId) {
        log.debug("Fetching review with ID: {}", reviewId);
        return reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Review", "id", reviewId));
    }

    public void validateDuplicateReview(Product product, Long userId) {
        log.debug("Validating duplicate review for product ID: {} and user ID: {}",
                product.getProductId(), userId);
        if (reviewRepository.existsByProductAndUserId(product, userId)) {
            log.warn("Duplicate review detected for product ID: {} and user ID: {}",
                    product.getProductId(), userId);
            throw new ResourceAlreadyExistsException(
                    "Review",
                    "userId",
                    userId
            );
        }
    }
}