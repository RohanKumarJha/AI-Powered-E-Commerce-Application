package com.ecommerce.repository;

import com.ecommerce.model.ProductImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends MongoRepository<ProductImage, Long> {
}