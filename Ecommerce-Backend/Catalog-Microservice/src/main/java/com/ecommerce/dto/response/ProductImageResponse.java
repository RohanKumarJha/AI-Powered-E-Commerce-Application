package com.ecommerce.dto.response;

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