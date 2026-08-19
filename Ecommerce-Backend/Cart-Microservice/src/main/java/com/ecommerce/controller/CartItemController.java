package com.ecommerce.controller;

import com.ecommerce.dto.request.CartItemRequest;
import com.ecommerce.dto.request.UpdateCartItemQuantityRequest;
import com.ecommerce.dto.response.CartItemResponse;
import com.ecommerce.service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart-items")
@RequiredArgsConstructor
@Slf4j
public class CartItemController {

    private final CartItemService cartItemService;


    @PostMapping
    public ResponseEntity<CartItemResponse> addItem(
            @Valid @RequestBody CartItemRequest request) {
        log.info("Add cart item API called.");
        return new ResponseEntity<>(
                cartItemService.addItem(request),
                HttpStatus.CREATED
        );
    }


    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<CartItemResponse>> getItemsByCart(
            @PathVariable Long cartId) {
        log.info("Get cart items API called for cart ID: {}", cartId);
        return ResponseEntity.ok(
                cartItemService.getItemsByCart(cartId)
        );
    }


    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItemResponse> getItemById(
            @PathVariable Long cartItemId) {
        log.info("Get cart item API called for cart item ID: {}", cartItemId);
        return ResponseEntity.ok(
                cartItemService.getItemById(cartItemId)
        );
    }


    @PutMapping("/{cartItemId}/quantity")
    public ResponseEntity<CartItemResponse> updateQuantity(
            @PathVariable Long cartItemId,
            @Valid @RequestBody UpdateCartItemQuantityRequest request) {
        log.info("Update cart item quantity API called for cart item ID: {}", cartItemId);
        return ResponseEntity.ok(
                cartItemService.updateQuantity(cartItemId, request)
        );
    }


    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> removeItem(
            @PathVariable Long cartItemId) {
        log.info("Remove cart item API called for cart item ID: {}", cartItemId);
        cartItemService.removeItem(cartItemId);
        return ResponseEntity.noContent().build();
    }
}