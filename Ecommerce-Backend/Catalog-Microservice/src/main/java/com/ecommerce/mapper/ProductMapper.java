package com.ecommerce.mapper;

import com.ecommerce.dto.request.ProductRequest;
import com.ecommerce.dto.response.ProductResponse;
import com.ecommerce.model.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ModelMapper modelMapper;

    public Product toEntity(ProductRequest request) {
        return modelMapper.map(request, Product.class);
    }

    public ProductResponse toResponse(Product product) {
        return modelMapper.map(product, ProductResponse.class);
    }

    public void updateFromRequest(ProductRequest request, Product product) {
        modelMapper.map(request, product);
    }
}