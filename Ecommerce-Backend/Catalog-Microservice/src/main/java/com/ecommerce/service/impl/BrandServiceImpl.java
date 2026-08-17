package com.ecommerce.service.impl;

import com.ecommerce.dto.request.BrandRequest;
import com.ecommerce.dto.response.BrandResponse;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.service.factory.BrandFactory;
import com.ecommerce.mapper.BrandMapper;
import com.ecommerce.model.Brand;
import com.ecommerce.repository.BrandRepository;
import com.ecommerce.service.BrandReferenceService;
import com.ecommerce.service.BrandService;
import com.ecommerce.security.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandFactory brandFactory;
    private final BrandMapper brandMapper;
    private final BrandReferenceService brandReferenceService;

    @Override
    public BrandResponse createBrand(BrandRequest request) {

        Brand brand = brandFactory.create(request);

        Long userId = UserContext.getCurrentUserId();
        brand.setCreatedBy(userId);
        brand.setUpdatedBy(userId);
        Brand savebrand = brandRepository.save(brand);
        log.info("Brand Created Successfully with brand id : {}", savebrand.getBrandId());
        return brandMapper.toResponse(savebrand);
    }

    @Override
    public BrandResponse updateBrand(Long brandId, BrandRequest request) {

        Brand brand = brandFactory.getById(brandId);

        brandFactory.validateBrandNameForUpdate(
                brandId,
                request.getName()
        );

        brandMapper.updateFromRequest(request, brand);

        brand.setUpdatedBy(UserContext.getCurrentUserId());
        Brand savebrand = brandRepository.save(brand);
        log.info("Brand Updated Successfully for brand id : {}",brandId);
        return brandMapper.toResponse(savebrand);
    }

    @Override
    public void deleteBrand(Long brandId) {

        Brand brand = brandFactory.getById(brandId);

        if (brandReferenceService.isBrandInUse(brandId)) {
            throw new BadRequestException(
                    "Brand cannot be deleted because it is associated with one or more products."
            );
        }

        brandRepository.delete(brand);
        log.info("Brand Deleted Successfully for brand id : {}",brandId);
    }

    @Override
    public BrandResponse getBrandById(Long brandId) {

        Brand brand = brandFactory.getById(brandId);
        log.info("Brand fetched successfully with id: {}", brandId);
        return brandMapper.toResponse(brand);
    }

    @Override
    public List<BrandResponse> getAllBrands() {

        List<BrandResponse> responses = brandRepository.findAll()
                .stream()
                .map(brandMapper::toResponse)
                .toList();
        log.info("Fetched {} brands successfully.",responses.size());
        return responses;
    }
}