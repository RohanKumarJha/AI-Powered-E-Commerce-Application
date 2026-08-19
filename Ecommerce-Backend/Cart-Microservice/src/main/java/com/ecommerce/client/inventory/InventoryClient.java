package com.ecommerce.client.inventory;

public interface InventoryClient {

    void decreaseStock(Long productId, Integer quantity);

    void increaseStock(Long productId, Integer quantity);
}
