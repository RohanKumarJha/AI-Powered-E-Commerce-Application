package com.ecommerce.service.impl;

import com.ecommerce.dto.request.ReviewRequest;
import com.ecommerce.dto.response.ReviewResponse;
import com.ecommerce.service.factory.ReviewFactory;
import com.ecommerce.mapper.ReviewMapper;
import com.ecommerce.model.Product;
import com.ecommerce.model.Review;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.ReviewRepository;
import com.ecommerce.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ReviewFactory reviewFactory;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewResponse addReview(Long productId, ReviewRequest request) {
        Product product = reviewFactory.getProduct(productId);
        reviewFactory.validateDuplicateReview(
                product,
                request.getUserId()
        );
        Review review = reviewFactory.create(request);
        review.setProduct(product);
        Review savedReview = reviewRepository.save(review);
        log.info("Review saved successfully with ID: {} for product ID: {}",
                savedReview.getReviewId(), productId);
        updateProductRating(product);
        log.info("Product rating updated successfully for product ID: {}", productId);
        return reviewMapper.toResponse(savedReview);
    }

    @Override
    public ReviewResponse updateReview(Long reviewId,
                                       ReviewRequest request) {
        Review review = reviewFactory.getReview(reviewId);
        reviewMapper.updateFromRequest(request, review);
        Review updatedReview = reviewRepository.save(review);
        log.info("Review updated successfully with ID: {}", reviewId);
        updateProductRating(review.getProduct());
        log.info("Product rating updated successfully for product ID: {}",
                review.getProduct().getProductId());
        return reviewMapper.toResponse(updatedReview);
    }

    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewFactory.getReview(reviewId);
        Product product = review.getProduct();
        reviewRepository.delete(review);
        log.info("Review deleted successfully with ID: {}", reviewId);
        updateProductRating(product);
        log.info("Product rating updated successfully for product ID: {}",
                product.getProductId());
    }

    @Override
    public List<ReviewResponse> getProductReviews(Long productId) {
        Product product = reviewFactory.getProduct(productId);
        List<Review> reviews = reviewRepository.findByProduct(product);
        log.info("Successfully fetched {} reviews for product ID: {}",
                reviews.size(), productId);
        return reviews.stream()
                .map(reviewMapper::toResponse)
                .toList();
    }

// ************************ Helper Method ********************************
    private void updateProductRating(Product product) {
        List<Review> reviews = reviewRepository.findByProduct(product);
        int reviewCount = reviews.size();
        double averageRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
        product.setReviewCount(reviewCount);
        product.setAverageRating(averageRating);
        productRepository.save(product);
        log.info(
                "Product rating updated successfully. Product ID: {}, Average Rating: {}, Review Count: {}",
                product.getProductId(),
                averageRating,
                reviewCount
        );
    }
}