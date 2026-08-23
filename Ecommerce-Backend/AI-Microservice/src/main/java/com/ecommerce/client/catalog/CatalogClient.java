package com.ecommerce.client.catalog;

import com.ecommerce.client.catalog.response.MockProductResponse;
import com.ecommerce.client.catalog.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatalogClient {

    public List<ProductResponse> getProducts() {
        return MockProductResponse.getProducts();
    }

}