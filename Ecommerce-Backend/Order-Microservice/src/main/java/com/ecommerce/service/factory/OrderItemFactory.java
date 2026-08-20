package com.ecommerce.service.factory;

import com.ecommerce.client.cart.response.CartItemResponse;
import com.ecommerce.client.catalog.response.ProductImageResponse;
import com.ecommerce.client.catalog.response.ProductResponse;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Objects;

@Component
public class OrderItemFactory {

    private static final BigDecimal DEFAULT_DISCOUNT = BigDecimal.ZERO;

    public OrderItem createOrderItem(Order order, CartItemResponse cartItem, ProductResponse productResponse) {
        validateOrder(order);
        validateCartItem(cartItem);
        validateProduct(productResponse);
        return OrderItem.builder()
                .order(order)
                .productId(productResponse.getProductId())
                .sellerId(productResponse.getSellerId())
                .productNameSnapshot(productResponse.getName())
                .skuSnapshot(productResponse.getSku())
                .mainImageUrlSnapshot(getPrimaryImage(productResponse))
                .priceSnapshot(productResponse.getPrice())
                .discountSnapshot(
                        (productResponse.getDiscount() == null)
                                ?  DEFAULT_DISCOUNT
                                : productResponse.getDiscount()
                )
                .specialPriceSnapshot(productResponse.getSpecialPrice())
                .quantity(cartItem.getQuantity())
                .lineTotal(calculateLineTotal(productResponse.getSpecialPrice(),cartItem.getQuantity()))
                .build();
    }


//   ----------------------------- Additional Methods --------------------------------------

    private void validateOrder(Order order) {
        if(order == null) {
            throw new BadRequestException("Order cannot be null");
        }
    }

    private void validateCartItem(CartItemResponse cartItem) {
        if(cartItem == null) {
            throw new BadRequestException("Cart Item cannot be null");
        }
        if(cartItem.getProductId() == null) {
            throw new BadRequestException("Product id is required");
        }
        validateQuantity(cartItem.getQuantity());
    }

    private void validateQuantity(Integer quantity) {
        if(quantity == null) {
            throw new BadRequestException("Quantity is required");
        }
        if(quantity <= 0) {
            throw new BadRequestException("Quantity must be greater than zero");
        }
    }

    private void validateProduct(ProductResponse productResponse) {
        if(productResponse == null) {
            throw new BadRequestException("Product not found");
        }
        if(Boolean.FALSE.equals(productResponse.getActive())) {
            throw new BadRequestException("Product is inactive");
        }
        if(productResponse.getPrice() == null) {
            throw new BadRequestException("Product price is missing");
        }
        if(productResponse.getSpecialPrice() == null) {
            throw new BadRequestException("Product special price is missing");
        }
    }

    private String getPrimaryImage(ProductResponse product) {
        if (product.getImages() == null
                || product.getImages().isEmpty()) {
            return null;
        }
        return product.getImages()
                .stream()
                .filter(ProductImageResponse::getPrimaryImage)
                .findFirst()
                .orElse(
                        product.getImages()
                                .stream()
                                .min(
                                        Comparator.comparing(
                                                ProductImageResponse::getDisplayOrder
                                        )
                                )
                                .orElse(null)
                )
                .getImageUrl();
    }

    private BigDecimal calculateLineTotal(
            BigDecimal specialPrice,
            Integer quantity) {
        Objects.requireNonNull(
                specialPrice,
                "Special price cannot be null."
        );
        Objects.requireNonNull(
                quantity,
                "Quantity cannot be null."
        );
        return specialPrice
                .multiply(BigDecimal.valueOf(quantity))
                .setScale(2, RoundingMode.HALF_UP);
    }

}