package com.ecommerce.repository;

import com.ecommerce.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, Long> {

    boolean existsByProductIdAndUserId(Long productId, Long userId);

    List<Review> findByProductId(Long productId);
}