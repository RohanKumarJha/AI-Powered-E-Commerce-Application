package com.ecommerce.repository;

import com.ecommerce.model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends MongoRepository<Brand, Long> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Brand> findByNameIgnoreCase(String name);
}