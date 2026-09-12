package com.ecommerce.repository;

import com.ecommerce.dto.request.ProductFilterRequest;
import com.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepository {

    Page<Product> filterProducts(ProductFilterRequest request, Pageable pageable);
}