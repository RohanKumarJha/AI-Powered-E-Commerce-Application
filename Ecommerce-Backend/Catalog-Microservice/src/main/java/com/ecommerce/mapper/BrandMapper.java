package com.ecommerce.mapper;

import com.ecommerce.dto.request.BrandRequest;
import com.ecommerce.dto.response.BrandResponse;
import com.ecommerce.model.Brand;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandMapper {

    private final ModelMapper modelMapper;

    public Brand toEntity(BrandRequest request) {
        return modelMapper.map(request, Brand.class);
    }

    public BrandResponse toResponse(Brand brand) {
        return modelMapper.map(brand, BrandResponse.class);
    }

    public void updateFromRequest(BrandRequest request, Brand brand) {
        modelMapper.map(request, brand);
    }
}