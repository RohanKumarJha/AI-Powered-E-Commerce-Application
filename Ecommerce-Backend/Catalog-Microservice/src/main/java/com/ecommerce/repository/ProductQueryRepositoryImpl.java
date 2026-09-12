package com.ecommerce.repository;

import com.ecommerce.dto.request.ProductFilterRequest;
import com.ecommerce.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Product> filterProducts(
            ProductFilterRequest request,
            Pageable pageable) {

        Criteria criteria = new Criteria();

        if (request.getKeyword() != null && !request.getKeyword().isBlank()) {
            criteria.and("name")
                    .regex(request.getKeyword(), "i");
        }

        if (request.getCategoryId() != null) {
            criteria.and("categoryId")
                    .is(request.getCategoryId());
        }

        if (request.getBrandId() != null) {
            criteria.and("brandId")
                    .is(request.getBrandId());
        }

        if (request.getMinPrice() != null) {
            criteria.and("price")
                    .gte(request.getMinPrice());
        }

        if (request.getMaxPrice() != null) {
            criteria.and("price")
                    .lte(request.getMaxPrice());
        }

        if (request.getMinimumRating() != null) {
            criteria.and("averageRating")
                    .gte(request.getMinimumRating());
        }

        if (request.getActive() != null) {
            criteria.and("active")
                    .is(request.getActive());
        }

        Query query = new Query(criteria);

        long total = mongoTemplate.count(query, Product.class);

        query.with(pageable);

        List<Product> products =
                mongoTemplate.find(query, Product.class);

        return new PageImpl<>(products, pageable, total);
    }
}