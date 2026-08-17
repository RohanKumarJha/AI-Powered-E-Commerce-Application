package com.ecommerce.service.factory;

import com.ecommerce.dto.request.ProductRequest;
import com.ecommerce.exception.ResourceAlreadyExistsException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.model.Brand;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.BrandRepository;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Configuration
@Slf4j
public class ProductFactory {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductMapper productMapper;

    public Product create(ProductRequest request) {
        log.debug("Creating product entity for SKU: {}", request.getSku());
        validateDuplicateSku(request.getSku());
        Product product = productMapper.toEntity(request);
        Category category = getCategoryById(request.getCategoryId());
        Brand brand = getBrandById(request.getBrandId());
        product.setCategory(category);
        product.setBrand(brand);
        log.info("Product entity created successfully for SKU: {}", request.getSku());
        return product;
    }

    public Product getProductById(Long productId) {
        log.debug("Fetching product with ID: {}", productId);
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    public Category getCategoryById(Long categoryId) {
        log.debug("Fetching category with ID: {}", categoryId);
        return categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", categoryId));
    }

    public Brand getBrandById(Long brandId) {
        log.debug("Fetching brand with ID: {}", brandId);
        return brandRepository.findById(brandId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Brand", "id", brandId));
    }

    public void validateDuplicateSku(String sku) {
        log.debug("Validating duplicate SKU: {}", sku);
        if (productRepository.existsBySkuIgnoreCase(sku)) {
            log.warn("Duplicate SKU detected: {}", sku);
            throw new ResourceAlreadyExistsException("Product", "sku", sku);
        }
    }

    public void validateSkuForUpdate(Long productId, String sku) {
        log.info("Validating SKU '{}' for product update. Product ID: {}", sku, productId);
        productRepository.findBySkuIgnoreCase(sku)
                .ifPresent(existingProduct -> {
                    if (!existingProduct.getProductId().equals(productId)) {
                        log.warn("SKU '{}' already exists for Product ID: {}",
                                sku, existingProduct.getProductId());
                        throw new ResourceAlreadyExistsException("Product", "sku", sku);
                    }
                });
        log.info("SKU update validation passed for Product ID: {}", productId);
    }
}