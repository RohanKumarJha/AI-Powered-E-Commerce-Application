package com.ecommerce.service.factory;

import com.ecommerce.dto.request.ProductImageRequest;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.ProductImageMapper;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductImage;
import com.ecommerce.repository.ProductImageRepository;
import com.ecommerce.service.MongoSequenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductImageFactory {

    private final ProductImageRepository productImageRepository;
    private final ProductFactory productFactory;
    private final ProductImageMapper productImageMapper;
    private final MongoSequenceService mongoSequenceService;

    public ProductImage create(Long productId, ProductImageRequest request) {

        log.info(
                "Creating product image entity for product ID: {}",
                productId
        );

        Product product =
                productFactory.getProductById(productId);

        ProductImage productImage =
                productImageMapper.toEntity(request);

        Long productImageId =
                mongoSequenceService.getNextSequence("product_images");

        productImage.setProductImageId(productImageId);
        productImage.setProductId(product.getProductId());

        log.info(
                "Product image entity created successfully for product ID: {}",
                productId
        );

        return productImage;
    }

    public ProductImage getProductImageById(Long imageId) {

        log.info(
                "Fetching product image with ID: {}",
                imageId
        );

        return productImageRepository.findById(imageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product Image",
                                "id",
                                imageId
                        ));
    }
}