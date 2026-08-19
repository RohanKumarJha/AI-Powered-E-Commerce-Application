package com.ecommerce.controller;

import com.ecommerce.dto.request.UpdateCartStatusRequest;
import com.ecommerce.dto.response.CartResponse;
import com.ecommerce.dto.response.PageResponse;
import com.ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponse> createCart() {
        log.info("Create cart API called.");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartService.createCart());
    }

    @GetMapping
    public ResponseEntity<PageResponse<CartResponse>> getAllCarts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction) {
        log.info("Get all carts API called. Page: {}, Size: {}, SortBy: {}, Direction: {}",
                page, size, sortBy, direction);
        return ResponseEntity.ok(
                cartService.getAllCarts(
                        page,
                        size,
                        sortBy,
                        direction
                )
        );
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponse> getCartById(
            @PathVariable Long cartId) {
        log.info("Get cart by ID API called. Cart ID: {}", cartId);
        return ResponseEntity.ok(
                cartService.getCartById(cartId)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartResponse> getCartByUserId(
            @PathVariable Long userId) {
        log.info("Get cart by user ID API called. User ID: {}", userId);
        return ResponseEntity.ok(
                cartService.getCartByUserId(userId)
        );
    }

    @PutMapping("/{cartId}/status")
    public ResponseEntity<CartResponse> updateCartStatus(
            @PathVariable Long cartId,
            @Valid @RequestBody UpdateCartStatusRequest request) {
        log.info("Update cart status API called. Cart ID: {}", cartId);
        return ResponseEntity.ok(
                cartService.updateCartStatus(cartId, request)
        );
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(
            @PathVariable Long cartId) {
        log.info("Delete cart API called. Cart ID: {}", cartId);
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }
}