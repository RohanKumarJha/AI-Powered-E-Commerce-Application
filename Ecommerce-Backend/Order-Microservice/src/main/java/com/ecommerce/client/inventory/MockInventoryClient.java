package com.ecommerce.client.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MockInventoryClient implements InventoryClient {

    @Override
    public Integer getAvailableQuantity(Long productId) {
        log.info("Fetching mock available quantity for productId: {}", productId);
        Integer availableQuantity = 100;
        log.debug(
                "Returning mock available quantity: {} for productId: {}",
                availableQuantity,
                productId
        );
        return availableQuantity;
    }

    @Override
    public void reduceStock(Long productId, Integer quantity) {
        log.info(
                "Reducing mock stock for productId: {} by quantity: {}",
                productId,
                quantity
        );
        log.debug(
                "Mock stock reduction completed for productId: {}",
                productId
        );
    }
}