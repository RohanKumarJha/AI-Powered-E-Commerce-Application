package com.ecommerce.specification;

import com.ecommerce.dto.request.ProductFilterRequest;
import com.ecommerce.model.Product;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    private ProductSpecification() {
    }

    public static Specification<Product> filter(ProductFilterRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getKeyword() != null && !request.getKeyword().isBlank()) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + request.getKeyword().toLowerCase() + "%"
                        )
                );
            }
            if (request.getCategoryId() != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("category").get("categoryId"),
                                request.getCategoryId()
                        )
                );
            }
            if (request.getBrandId() != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("brand").get("brandId"),
                                request.getBrandId()
                        )
                );
            }
            if (request.getMinPrice() != null) {
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("price"),
                                request.getMinPrice()
                        )
                );
            }
            if (request.getMaxPrice() != null) {
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("price"),
                                request.getMaxPrice()
                        )
                );
            }
            if (request.getMinimumRating() != null) {
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("averageRating"),
                                request.getMinimumRating()
                        )
                );
            }
            if (request.getActive() != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("active"),
                                request.getActive()
                        )
                );
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}