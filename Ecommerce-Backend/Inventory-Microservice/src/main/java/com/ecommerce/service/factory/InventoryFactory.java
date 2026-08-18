package com.ecommerce.service.factory;

import com.ecommerce.dto.request.InventoryRequest;
import com.ecommerce.exception.ResourceAlreadyExistsException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.InventoryMapper;
import com.ecommerce.model.Inventory;
import com.ecommerce.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryFactory {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public Inventory create(InventoryRequest request) {
        log.debug("Creating inventory for productId: {}", request.getProductId());
        validateProductId(request.getProductId());
        validateSku(request.getSku());
        log.debug("Mapping InventoryRequest to Inventory entity");
        return inventoryMapper.toEntity(request);
    }

    public Inventory getById(Long inventoryId) {
        log.debug("Fetching inventory with id: {}", inventoryId);
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventory",
                        "id",
                        inventoryId)
                );
    }

    public Inventory getByProductId(Long productId) {
        log.debug("Fetching inventory with productId: {}", productId);
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Inventory",
                            "productId",
                            productId
                    )
                );
    }

    public void validateProductId(Long productId) {
        log.debug("Validating productId uniqueness: {}", productId);
        if (inventoryRepository.existsByProductId(productId)) {
            throw new ResourceAlreadyExistsException(
                    "Inventory",
                    "productId",
                    productId
            );
        }
    }

    public void validateSku(String sku) {
        log.debug("Validating SKU uniqueness: {}", sku);
        if (inventoryRepository.existsBySkuIgnoreCase(sku)) {
            throw new ResourceAlreadyExistsException(
                    "Inventory",
                    "sku",
                    sku
            );
        }
    }

    public void validateSkuForUpdate(Long inventoryId, String sku) {
        log.debug(
                "Validating SKU '{}' for inventory update: {}",
                sku,
                inventoryId
        );
        if (inventoryRepository.existsBySkuIgnoreCaseAndInventoryIdNot(
                sku,
                inventoryId)) {
            throw new ResourceAlreadyExistsException(
                    "Inventory",
                    "sku",
                    sku
            );
        }
    }
}