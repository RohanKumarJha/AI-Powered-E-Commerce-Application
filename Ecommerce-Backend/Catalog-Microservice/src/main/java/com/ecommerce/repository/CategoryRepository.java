package com.ecommerce.repository;

import com.ecommerce.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Long> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Category> findByNameIgnoreCase(String name);

    boolean existsByParentCategoryId(Long categoryId);
}