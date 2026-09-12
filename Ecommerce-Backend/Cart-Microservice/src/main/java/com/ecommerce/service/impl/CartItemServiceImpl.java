package com.ecommerce.service.impl;

import com.ecommerce.client.inventory.InventoryClient;
import com.ecommerce.dto.request.CartItemRequest;
import com.ecommerce.dto.request.UpdateCartItemQuantityRequest;
import com.ecommerce.dto.response.CartItemResponse;
import com.ecommerce.mapper.CartItemMapper;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.service.CartItemService;
import com.ecommerce.service.factory.CartItemFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartItemServiceImpl implements CartItemService {

    private final CartRepository cartRepository;
    private final CartItemFactory cartItemFactory;
    private final CartItemMapper cartItemMapper;
    private final InventoryClient inventoryClient;

    @Override
    @Transactional
    public CartItemResponse addItem(CartItemRequest request) {

        Cart cart = cartItemFactory.getCartById(request.getCartId());

        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item ->
                        item.getProductId().equals(request.getProductId()))
                .findFirst()
                .orElse(null);

        if (cartItem != null) {

            cartItem.setQuantity(
                    cartItem.getQuantity() + request.getQuantity()
            );

            cartItem.setLineTotal(
                    cartItem.getSpecialPriceSnapshot()
                            .multiply(
                                    BigDecimal.valueOf(cartItem.getQuantity())
                            )
            );

        } else {

            cartItem = cartItemFactory.createCartItem(cart, request);
            cart.getCartItems().add(cartItem);
        }

        log.info(
                "Cart item saved successfully with ID: {}",
                cartItem.getCartItemId()
        );

        log.info(
                "Decreasing inventory for product ID: {} by quantity: {}",
                request.getProductId(),
                request.getQuantity()
        );

        inventoryClient.decreaseStock(
                request.getProductId(),
                request.getQuantity()
        );

        recalculateCart(cart);

        cartRepository.save(cart);

        log.info(
                "Cart updated successfully for cart ID: {}",
                cart.getCartId()
        );

        return cartItemMapper.toResponse(cartItem);
    }

    @Override
    public List<CartItemResponse> getItemsByCart(Long cartId) {

        Cart cart = cartItemFactory.getCartById(cartId);

        List<CartItem> cartItems =
                new ArrayList<>(cart.getCartItems());

        log.info(
                "Fetched {} cart items for cart ID: {}",
                cartItems.size(),
                cartId
        );

        return cartItems.stream()
                .map(cartItemMapper::toResponse)
                .toList();
    }

    @Override
    public CartItemResponse getItemById(Long cartItemId) {

        CartItem cartItem = findCartItemById(cartItemId);

        log.info(
                "Cart item fetched successfully with ID: {}",
                cartItemId
        );

        return cartItemMapper.toResponse(cartItem);
    }

    @Override
    @Transactional
    public CartItemResponse updateQuantity(
            Long cartItemId,
            UpdateCartItemQuantityRequest request) {

        Cart cart = findCartByItemId(cartItemId);

        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item ->
                        item.getCartItemId().equals(cartItemId))
                .findFirst()
                .orElseThrow();

        int oldQuantity = cartItem.getQuantity();
        int newQuantity = request.getQuantity();
        int difference = newQuantity - oldQuantity;

        cartItem.setQuantity(newQuantity);

        cartItem.setLineTotal(
                cartItem.getSpecialPriceSnapshot()
                        .multiply(BigDecimal.valueOf(newQuantity))
        );

        log.info(
                "Cart item quantity updated from {} to {}",
                oldQuantity,
                newQuantity
        );

        if (difference > 0) {

            inventoryClient.decreaseStock(
                    cartItem.getProductId(),
                    difference
            );

        } else if (difference < 0) {

            inventoryClient.increaseStock(
                    cartItem.getProductId(),
                    Math.abs(difference)
            );
        }

        recalculateCart(cart);

        cartRepository.save(cart);

        log.info(
                "Cart updated successfully for cart ID: {}",
                cart.getCartId()
        );

        return cartItemMapper.toResponse(cartItem);
    }

    @Override
    @Transactional
    public void removeItem(Long cartItemId) {

        Cart cart = findCartByItemId(cartItemId);

        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item ->
                        item.getCartItemId().equals(cartItemId))
                .findFirst()
                .orElseThrow();

        inventoryClient.increaseStock(
                cartItem.getProductId(),
                cartItem.getQuantity()
        );

        cart.getCartItems().remove(cartItem);

        log.info(
                "Cart item deleted successfully with ID: {}",
                cartItemId
        );

        recalculateCart(cart);

        cartRepository.save(cart);

        log.info(
                "Cart updated successfully after item removal. Cart ID: {}",
                cart.getCartId()
        );
    }

    // ************************ Helper Methods *******************************

    private void recalculateCart(Cart cart) {

        List<CartItem> cartItems =
                new ArrayList<>(cart.getCartItems());

        int totalItems = cartItems.size();

        int totalQuantity = cartItems.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();

        BigDecimal totalAmount = cartItems.stream()
                .map(item ->
                        item.getPriceSnapshot()
                                .multiply(
                                        BigDecimal.valueOf(
                                                item.getQuantity()
                                        )
                                )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal grandTotal = cartItems.stream()
                .map(CartItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDiscount =
                totalAmount.subtract(grandTotal);

        cart.setTotalItems(totalItems);
        cart.setTotalQuantity(totalQuantity);
        cart.setTotalAmount(totalAmount);
        cart.setTotalDiscount(totalDiscount);
        cart.setGrandTotal(grandTotal);

        log.info(
                "Cart recalculated successfully. Cart ID: {}, " +
                        "Total Items: {}, Total Quantity: {}, Grand Total: {}",
                cart.getCartId(),
                totalItems,
                totalQuantity,
                grandTotal
        );
    }

    private CartItem findCartItemById(Long cartItemId) {

        return StreamSupport.stream(
                        cartRepository.findAll().spliterator(),
                        false
                )
                .flatMap(cart -> cart.getCartItems().stream())
                .filter(item ->
                        item.getCartItemId().equals(cartItemId))
                .findFirst()
                .orElseThrow();
    }

    private Cart findCartByItemId(Long cartItemId) {

        return StreamSupport.stream(
                        cartRepository.findAll().spliterator(),
                        false
                )
                .filter(cart ->
                        cart.getCartItems()
                                .stream()
                                .anyMatch(item ->
                                        item.getCartItemId()
                                                .equals(cartItemId)))
                .findFirst()
                .orElseThrow();
    }
}