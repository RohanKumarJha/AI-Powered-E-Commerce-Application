package com.ecommerce.service.factory;

import com.ecommerce.dto.request.BrandRequest;
import com.ecommerce.exception.ResourceAlreadyExistsException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.BrandMapper;
import com.ecommerce.model.Brand;
import com.ecommerce.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BrandFactory {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public Brand create(BrandRequest request) {
        log.debug("Creating brand entity.");
        validateDuplicateBrandName(request.getName());
        return brandMapper.toEntity(request);
    }

    public Brand getById(Long brandId) {
        log.debug("Fetching brand entity with id: {}", brandId);
        return brandRepository.findById(brandId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Brand",
                                "id",
                                brandId
                        ));
    }

    public void validateDuplicateBrandName(String name) {
        log.debug("Validating duplicate brand name.");
        if (brandRepository.existsByNameIgnoreCase(name)) {
            throw new ResourceAlreadyExistsException(
                    "Brand",
                    "name",
                    name
            );
        }
    }

    public void validateBrandNameForUpdate(Long brandId, String name) {
        log.debug("Validating brand name for update. Brand id: {}", brandId);
        brandRepository.findByNameIgnoreCase(name)
                .ifPresent(existingBrand -> {
                    if (!existingBrand.getBrandId().equals(brandId)) {
                        log.warn("Brand already exists with name: {}", name);
                        throw new ResourceAlreadyExistsException(
                                "Brand",
                                "name",
                                name
                        );
                    }
                });
    }
}