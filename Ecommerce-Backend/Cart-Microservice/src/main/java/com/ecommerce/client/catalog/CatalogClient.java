package com.ecommerce.client.catalog;

import com.ecommerce.client.product.ProductResponse;

public interface CatalogClient {

    ProductResponse getProductById(Long productId);
}
