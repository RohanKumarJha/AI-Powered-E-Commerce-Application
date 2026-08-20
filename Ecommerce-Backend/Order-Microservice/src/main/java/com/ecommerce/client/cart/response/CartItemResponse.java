package com.ecommerce.client.cart.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponse {

    private Long cartItemId;

    private Long productId;

    private Integer quantity;
}