package com.ecommerce.mapper;

import com.ecommerce.dto.request.ProductImageRequest;
import com.ecommerce.dto.response.ProductImageResponse;
import com.ecommerce.model.ProductImage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductImageMapper {

    private final ModelMapper modelMapper;

    public ProductImage toEntity(ProductImageRequest request) {
        return modelMapper.map(request, ProductImage.class);
    }

    public ProductImageResponse toResponse(ProductImage productImage) {
        return modelMapper.map(productImage, ProductImageResponse.class);
    }

    public void updateFromRequest(ProductImageRequest request, ProductImage productImage) {
        modelMapper.map(request, productImage);
    }
}