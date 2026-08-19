package com.ecommerce.service.factory;

import com.ecommerce.exception.ResourceAlreadyExistsException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Cart;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.security.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartFactory {

    private final CartRepository cartRepository;

    public Cart createCart() {
        Long userId = UserContext.getCurrentUserId();
        log.debug("Creating cart entity for user ID: {}", userId);
        validateDuplicateCart(userId);
        log.debug("Cart entity created successfully for user ID: {}", userId);
        return Cart.builder()
                .userId(userId)
                .build();
    }

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

    public Cart getCartByUserId(Long userId) {
        log.debug("Fetching cart for user ID: {}", userId);
        return cartRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart",
                                "userId",
                                userId
                        ));
    }

    public void validateDuplicateCart(Long userId) {
        log.debug("Validating duplicate cart for user ID: {}", userId);
        if (cartRepository.existsByUserId(userId)) {
            log.warn("Duplicate cart detected for user ID: {}", userId);
            throw new ResourceAlreadyExistsException(
                    "Cart",
                    "userId",
                    userId
            );
        }
    }
}