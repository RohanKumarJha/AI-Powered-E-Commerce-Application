package com.ecommerce.client.catelog;

import com.ecommerce.client.product.ProductResponse;

public interface CatalogClient {

    ProductResponse getProductById(Long productId);
}
