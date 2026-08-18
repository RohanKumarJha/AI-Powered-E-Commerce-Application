package com.ecommerce.service;

import com.ecommerce.dto.request.InventoryRequest;
import com.ecommerce.dto.request.UpdateInventoryRequest;
import com.ecommerce.dto.request.UpdateStockRequest;
import com.ecommerce.dto.response.InventoryResponse;
import com.ecommerce.dto.response.PageResponse;

public interface InventoryService {

    InventoryResponse createInventory(InventoryRequest request);

    PageResponse<InventoryResponse> getAllInventories(
            Integer page,
            Integer size,
            String sortBy,
            String sortDir
    );

    InventoryResponse getInventoryById(Long inventoryId);

    InventoryResponse getInventoryByProductId(Long productId);

    InventoryResponse updateInventory(
            Long inventoryId,
            UpdateInventoryRequest request
    );

    InventoryResponse updateStock(
            Long inventoryId,
            UpdateStockRequest request
    );

    void deleteInventory(Long inventoryId);
}