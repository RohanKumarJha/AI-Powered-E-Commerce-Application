package com.ecommerce.client.inventory;

public interface InventoryClient {

    Integer getAvailableQuantity(Long productId);

    void reduceStock(Long productId, Integer quantity);

}