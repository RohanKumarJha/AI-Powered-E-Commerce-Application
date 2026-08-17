package com.ecommerce.controller;

import com.ecommerce.dto.request.ProductFilterRequest;
import com.ecommerce.dto.request.ProductImageRequest;
import com.ecommerce.dto.request.ProductRequest;
import com.ecommerce.dto.response.ProductImageResponse;
import com.ecommerce.dto.response.ProductResponse;
import com.ecommerce.dto.response.PageResponse;
import com.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest request) {
        log.info("Create Product Api Called !!");
        return productService.createProduct(request);
    }

    @PutMapping("/{productId}")
    public ProductResponse updateProduct(@PathVariable Long productId,
                                         @Valid @RequestBody ProductRequest request) {
        log.info("Update Api Product Called!!");
        return productService.updateProduct(productId, request);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        log.info("Delete Product Api is called!!");
        productService.deleteProduct(productId);
    }

    @GetMapping("/{productId}")
    public ProductResponse getProductById(@PathVariable Long productId) {
        log.info("Product is called with id : {}",productId);
        return productService.getProductById(productId);
    }

    @GetMapping
    public PageResponse<ProductResponse> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir) {
        log.info("Fetch All Product Api");
        return productService.getAllProducts(page, size, sortBy, sortDir);
    }

    @GetMapping("/search")
    public PageResponse<ProductResponse> searchProducts(
            @RequestParam String keyword,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir) {
        log.info("Search Products API called with keyword: {}", keyword);
        return productService.searchProducts(keyword, page, size, sortBy, sortDir);
    }

    @PostMapping("/filter")
    public PageResponse<ProductResponse> filterProducts(
            @RequestBody ProductFilterRequest request,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir) {
        log.info("Filter Product Api called");
        return productService.filterProducts(request, page, size, sortBy, sortDir);
    }

    @PostMapping("/{productId}/images")
    public ProductImageResponse uploadProductImage(@PathVariable Long productId,
                                                   @Valid @RequestBody ProductImageRequest request) {
        log.info("Upload image Api request called for productId: {}", productId);
        return productService.uploadProductImage(productId, request);
    }

    @DeleteMapping("/images/{imageId}")
    public void deleteProductImage(@PathVariable Long imageId) {
        log.info("Delete Product Image API called for imageId: {}", imageId);
        productService.deleteProductImage(imageId);
    }
}