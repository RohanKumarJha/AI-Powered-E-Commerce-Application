package com.ecommerce.service.impl;

import com.ecommerce.service.BrandReferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BrandReferenceServiceImpl implements BrandReferenceService {

    @Override
    public boolean isBrandInUse(Long brandId) {
        log.info("Checking whether brand with ID: {} is in use.", brandId);
        return false;
    }
}