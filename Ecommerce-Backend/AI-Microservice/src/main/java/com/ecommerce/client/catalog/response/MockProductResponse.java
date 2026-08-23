package com.ecommerce.client.catalog.response;

import java.math.BigDecimal;
import java.util.List;

public final class MockProductResponse {

    private MockProductResponse() {
    }

    public static List<ProductResponse> getProducts() {

        return List.of(

                ProductResponse.builder()
                        .productId(1L)
                        .name("Apple iPhone 16")
                        .category("Mobile")
                        .brand("Apple")
                        .price(BigDecimal.valueOf(89999))
                        .rating(4.8)
                        .active(true)
                        .build(),

                ProductResponse.builder()
                        .productId(2L)
                        .name("Samsung Galaxy S25")
                        .category("Mobile")
                        .brand("Samsung")
                        .price(BigDecimal.valueOf(79999))
                        .rating(4.7)
                        .active(true)
                        .build(),

                ProductResponse.builder()
                        .productId(3L)
                        .name("Sony WH-1000XM6")
                        .category("Headphone")
                        .brand("Sony")
                        .price(BigDecimal.valueOf(29999))
                        .rating(4.9)
                        .active(true)
                        .build(),

                ProductResponse.builder()
                        .productId(4L)
                        .name("Dell XPS 15")
                        .category("Laptop")
                        .brand("Dell")
                        .price(BigDecimal.valueOf(149999))
                        .rating(4.8)
                        .active(true)
                        .build(),

                ProductResponse.builder()
                        .productId(5L)
                        .name("Apple Watch Series 11")
                        .category("Smart Watch")
                        .brand("Apple")
                        .price(BigDecimal.valueOf(49999))
                        .rating(4.7)
                        .active(true)
                        .build()

        );
    }

}