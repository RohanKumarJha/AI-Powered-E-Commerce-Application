package com.ecommerce.repository;

import com.ecommerce.model.Product;
import com.ecommerce.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByProductAndUserId(Product product, Long userId);

    List<Review> findByProduct(Product product);
}