package com.ecommerce.service.impl;

import com.ecommerce.dto.request.UpdateCartStatusRequest;
import com.ecommerce.dto.response.CartResponse;
import com.ecommerce.dto.response.PageResponse;
import com.ecommerce.service.factory.CartFactory;
import com.ecommerce.mapper.CartMapper;
import com.ecommerce.model.Cart;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.service.CartService;
import com.ecommerce.util.PageRequestUtil;
import com.ecommerce.util.PageResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartFactory cartFactory;
    private final CartMapper cartMapper;

    @Override
    public CartResponse createCart() {
        Cart cart = cartFactory.createCart();
        cart = cartRepository.save(cart);
        log.info("Cart created successfully with ID: {}", cart.getCartId());
        return cartMapper.toResponse(cart);
    }

    @Override
    public PageResponse<CartResponse> getAllCarts(
            Integer page,
            Integer size,
            String sortBy,
            String direction) {
        Pageable pageable = PageRequestUtil.createPageRequest(
                page,
                size,
                sortBy,
                direction
        );
        Page<CartResponse> cartPage = cartRepository
                .findAll(pageable)
                .map(cartMapper::toResponse);
        log.info("Successfully fetched {} carts.", cartPage.getNumberOfElements());
        return PageResponseUtil.from(cartPage);
    }

    @Override
    public CartResponse getCartById(Long cartId) {
        CartResponse response = cartMapper.toResponse(cartFactory.getCartById(cartId));
        log.info("Cart fetched successfully with ID: {}", cartId);
        return response;
    }

    @Override
    public CartResponse getCartByUserId(Long userId) {
        CartResponse response = cartMapper.toResponse(cartFactory.getCartByUserId(userId));
        log.info("Cart fetched successfully for user ID: {}", userId);
        return response;
    }

    @Override
    public CartResponse updateCartStatus(
            Long cartId,
            UpdateCartStatusRequest request) {
        Cart cart = cartFactory.getCartById(cartId);
        cart.setCartStatus(request.getStatus());
        cart = cartRepository.save(cart);
        log.info("Cart status updated successfully for cart ID: {}", cartId);
        return cartMapper.toResponse(cart);
    }

    @Override
    public void deleteCart(Long cartId) {
        Cart cart = cartFactory.getCartById(cartId);
        cartRepository.delete(cart);
        log.info("Cart deleted successfully with ID: {}", cartId);
    }
}