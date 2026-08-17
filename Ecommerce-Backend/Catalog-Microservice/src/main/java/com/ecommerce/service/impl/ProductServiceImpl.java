package com.ecommerce.service.impl;

import com.ecommerce.dto.request.ProductFilterRequest;
import com.ecommerce.dto.request.ProductImageRequest;
import com.ecommerce.dto.request.ProductRequest;
import com.ecommerce.dto.response.ProductImageResponse;
import com.ecommerce.dto.response.ProductResponse;
import com.ecommerce.service.factory.ProductFactory;
import com.ecommerce.service.factory.ProductImageFactory;
import com.ecommerce.mapper.ProductImageMapper;
import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductImage;
import com.ecommerce.repository.ProductImageRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import com.ecommerce.dto.response.PageResponse;
import com.ecommerce.specification.ProductSpecification;
import com.ecommerce.security.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import com.ecommerce.util.PageRequestUtil;
import com.ecommerce.util.PageResponseUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductFactory productFactory;
    private final ProductImageFactory productImageFactory;
    private final ProductMapper productMapper;
    private final ProductImageMapper productImageMapper;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = productFactory.create(request);
        Long userId = UserContext.getCurrentUserId();
        product.setSellerId(userId);
        product.setCreatedBy(userId);
        product.setUpdatedBy(userId);
        product.setSpecialPrice(request.getPrice().subtract(request.getDiscount()));
        Product saveproduct = productRepository.save(product);
        log.info("Product saved in repository for product id : {}",saveproduct.getProductId());
        return productMapper.toResponse(saveproduct);
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest request) {
        Product product = productFactory.getProductById(productId);
        productFactory.validateSkuForUpdate(productId, request.getSku());
        productMapper.updateFromRequest(request, product);
        product.setCategory(productFactory.getCategoryById(request.getCategoryId()));
        product.setBrand(productFactory.getBrandById(request.getBrandId()));
        product.setSpecialPrice(request.getPrice().subtract(request.getDiscount()));
        product.setUpdatedBy(UserContext.getCurrentUserId());
        Product saveproduct = productRepository.save(product);
        log.info("Product saved in repository for product id : {}",saveproduct.getProductId());
        return productMapper.toResponse(saveproduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productFactory.getProductById(productId);
        productRepository.delete(product);
        log.info("Product deleted successfully");
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Product product = productFactory.getProductById(productId);
        log.info("Successfully fetched product for product id :{}", productId);
        return productMapper.toResponse(product);
    }

    @Override
    public PageResponse<ProductResponse> getAllProducts(
            Integer page,
            Integer size,
            String sortBy,
            String sortDir) {
        Page<ProductResponse> result =
                productRepository
                        .findAll(PageRequestUtil.createPageRequest(
                                page,
                                size,
                                sortBy,
                                sortDir))
                        .map(productMapper::toResponse);
        log.info("Successfully Fetched All Products");
        return PageResponseUtil.from(result);
    }

    @Override
    public PageResponse<ProductResponse> searchProducts(
            String keyword,
            Integer page,
            Integer size,
            String sortBy,
            String sortDir) {
        Page<ProductResponse> result =
                productRepository.findByNameContainingIgnoreCase(
                                keyword,
                                PageRequestUtil.createPageRequest(
                                        page,
                                        size,
                                        sortBy,
                                        sortDir))
                        .map(productMapper::toResponse);
        log.info("Product search completed. Total products found: {}",
                result.getTotalElements());
        return PageResponseUtil.from(result);
    }

    @Override
    public PageResponse<ProductResponse> filterProducts(
            ProductFilterRequest request,
            Integer page,
            Integer size,
            String sortBy,
            String sortDir) {
        Page<ProductResponse> result =
                productRepository
                        .findAll(
                                ProductSpecification.filter(request),
                                PageRequestUtil.createPageRequest(
                                        page,
                                        size,
                                        sortBy,
                                        sortDir))
                        .map(productMapper::toResponse);
        log.info("Product filtering completed. Total matching products: {}",
                result.getTotalElements());
        return PageResponseUtil.from(result);
    }

    @Override
    public List<ProductResponse> sortProducts(String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        List<ProductResponse> responses = productRepository.findAll(sort)
                .stream()
                .map(productMapper::toResponse)
                .toList();
        log.info("Successfully fetched {} sorted products.", responses.size());
        return responses;
    }

    @Override
    public Page<ProductResponse> getProducts(int page, int size) {
        Page<ProductResponse> pageRequest = productRepository.findAll(PageRequest.of(page, size))
                .map(productMapper::toResponse);
        log.info("Fetched {} products from page {}.",
                pageRequest.getNumberOfElements(), page);
        return pageRequest;
    }

    @Override
    public ProductImageResponse uploadProductImage(Long productId,
                                                   ProductImageRequest request) {
        ProductImage productImage =
                productImageFactory.create(productId, request);
        Long userId = UserContext.getCurrentUserId();
        productImage.setCreatedBy(userId);
        productImage.setUpdatedBy(userId);
        ProductImage savedImage = productImageRepository.save(productImage);

        log.info("Product image uploaded successfully with ID: {} for product ID: {}",
                savedImage.getProductImageId(), productId);
        return productImageMapper.toResponse(
                productImageRepository.save(productImage));
    }

    @Override
    public void deleteProductImage(Long imageId) {
        ProductImage productImage =
                productImageFactory.getProductImageById(imageId);
        productImageRepository.delete(productImage);
        log.info("Product image deleted successfully with ID: {}", imageId);
    }
}