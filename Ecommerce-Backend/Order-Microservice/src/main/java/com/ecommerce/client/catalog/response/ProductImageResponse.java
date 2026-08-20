package com.ecommerce.client.catalog.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageResponse {

    private Long productImageId;

    private String imageUrl;

    private Integer displayOrder;

    private Boolean primaryImage;
}