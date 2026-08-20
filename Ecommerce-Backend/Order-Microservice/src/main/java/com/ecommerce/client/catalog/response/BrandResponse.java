package com.ecommerce.client.catalog.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {

    private Long brandId;

    private String name;
}