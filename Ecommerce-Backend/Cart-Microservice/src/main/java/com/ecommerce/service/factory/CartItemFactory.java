package com.ecommerce.service.factory;

import com.ecommerce.client.product.ProductResponse;
import com.ecommerce.dto.request.CartItemRequest;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.client.catelog.CatalogClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartItemFactory {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CatalogClient catalogClient;

    public Cart getCartById(Long cartId) {
        log.debug("Fetching cart with ID: {}", cartId);
        return cartRepository.findById(cartId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart",
                                "id",
                                cartId
                        ));
    }

    public CartItem getCartItemById(Long cartItemId) {
        log.debug("Fetching cart item with ID: {}", cartItemId);
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart Item",
                                "id",
                                cartItemId
                        ));
    }

    public CartItem createCartItem(
            Cart cart,
            CartItemRequest request) {
        log.debug("Creating cart item for cart ID: {} and product ID: {}",
                cart.getCartId(),
                request.getProductId());
        log.debug("Fetching product details from Catalog Service for product ID: {}",
                request.getProductId());
        ProductResponse product =
                catalogClient.getProductById(request.getProductId());
        BigDecimal lineTotal = product.getSpecialPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .productId(product.getProductId())
                .productNameSnapshot(product.getName())
                .skuSnapshot(product.getSku())
                .mainImageUrlSnapshot(product.getMainImageUrl())
                .priceSnapshot(product.getPrice())
                .discountSnapshot(product.getDiscount())
                .specialPriceSnapshot(product.getSpecialPrice())
                .quantity(request.getQuantity())
                .lineTotal(lineTotal)
                .build();
        log.debug("Cart item entity created successfully for product ID: {}",
                product.getProductId());
        return cartItem;
    }
}