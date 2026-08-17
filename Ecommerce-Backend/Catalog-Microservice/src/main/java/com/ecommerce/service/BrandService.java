package com.ecommerce.service;

import com.ecommerce.dto.request.BrandRequest;
import com.ecommerce.dto.response.BrandResponse;

import java.util.List;

public interface BrandService {

    BrandResponse createBrand(BrandRequest request);

    BrandResponse updateBrand(Long brandId, BrandRequest request);

    void deleteBrand(Long brandId);

    BrandResponse getBrandById(Long brandId);

    List<BrandResponse> getAllBrands();
}