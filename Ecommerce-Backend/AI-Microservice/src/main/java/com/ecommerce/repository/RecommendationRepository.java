package com.ecommerce.repository;

import com.ecommerce.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendationRepository
        extends JpaRepository<Recommendation, Long> {

    Optional<Recommendation> findByUserIdAndActiveTrue(Long userId);

    boolean existsByUserId(Long userId);

}