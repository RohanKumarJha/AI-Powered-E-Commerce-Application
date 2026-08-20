package com.ecommerce.client.catalog;

import com.ecommerce.client.catalog.response.ProductResponse;

public interface CatalogClient {

    ProductResponse getProductById(Long productId);

}