package com.ecommerce.service.factory;

import com.ecommerce.dto.request.OrderRequest;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.model.Order;
import com.ecommerce.model.enums.OrderStatus;
import com.ecommerce.security.UserContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class OrderFactory {

    private static final BigDecimal DEFAULT_AMOUNT = BigDecimal.ZERO;
    private static final int ESTIMATED_DELIVERY_DAYS = 5;
    private static final BigDecimal TAX_RATE = new BigDecimal("0.18");
    private static final BigDecimal SHIPPING_CHARGE = new BigDecimal("50");
    private static final BigDecimal FREE_SHIPPING_LIMIT = new BigDecimal("500");

    public Order createOrder(OrderRequest orderRequest) {
        validateOrder(orderRequest);
        return Order.builder()
                .orderNumber(generateOrderNumber())
                .userId(UserContext.getCurrentUserId())
                .addressId(orderRequest.getAddressId())
                .paymentId(orderRequest.getPaymentMethod())
                .status(OrderStatus.PENDING)
                .subtotal(DEFAULT_AMOUNT)
                .discount(DEFAULT_AMOUNT)
                .shippingCharge(DEFAULT_AMOUNT)
                .tax(DEFAULT_AMOUNT)
                .totalAmount(DEFAULT_AMOUNT)
                .estimatedDeliveryDate(calculateEstimatedDeliveryDate())
                .build();
    }

    public void recalculateOrder(Order order) {
        BigDecimal subtotal = calculateSubtotal(order);
        BigDecimal discount = calculateDiscount(order);
        BigDecimal shipping = calculateShippingCharge(subtotal);
        BigDecimal tax = calculateTax(subtotal.subtract(discount));
        BigDecimal total = calculateTotalAmount(
                subtotal,
                discount,
                shipping,
                tax
        );
        order.setSubtotal(subtotal);
        order.setDiscount(discount);
        order.setShippingCharge(shipping);
        order.setTax(tax);
        order.setTotalAmount(total);
    }


//   ----------------------------- Additional Methods --------------------------------------

    private void validateOrder(OrderRequest orderRequest) {
        if(orderRequest == null) {
            throw new BadRequestException("Order cannot be null");
        }
        if(orderRequest.getAddressId() == null) {
            throw new BadRequestException("Address Id is required");
        }
        if(orderRequest.getPaymentMethod() == null) {
            throw new BadRequestException("Payment method is required");
        }
    }

    private String generateOrderNumber() {
        return "ORD-"+
                UUID.randomUUID()
                        .toString()
                        .replace("-","")
                        .substring(0,10).toUpperCase();
    }

    private LocalDate calculateEstimatedDeliveryDate() {
        return LocalDate.now().plusDays(ESTIMATED_DELIVERY_DAYS);
    }

    private BigDecimal calculateSubtotal(Order order) {
        if (order.getOrderItems() == null
                || order.getOrderItems().isEmpty()) {
            return BigDecimal.ZERO;
        }
        return order.getOrderItems()
                .stream()
                .map(item -> item.getLineTotal() == null
                        ? BigDecimal.ZERO
                        : item.getLineTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateDiscount(Order order) {
        if (order.getOrderItems() == null
                || order.getOrderItems().isEmpty()) {
            return BigDecimal.ZERO;
        }
        return order.getOrderItems()
                .stream()
                .map(item -> {
                    BigDecimal original =
                            item.getPriceSnapshot()
                                    .multiply(
                                            BigDecimal.valueOf(
                                                    item.getQuantity()));
                    BigDecimal discounted =
                            item.getSpecialPriceSnapshot()
                                    .multiply(
                                            BigDecimal.valueOf(
                                                    item.getQuantity()));
                    return original.subtract(discounted);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateShippingCharge(BigDecimal subtotal) {
        if (subtotal == null) {
            return SHIPPING_CHARGE;
        }
        if (subtotal.compareTo(FREE_SHIPPING_LIMIT) >= 0) {
            return BigDecimal.ZERO;
        }
        return SHIPPING_CHARGE;
    }

    private BigDecimal calculateTax(BigDecimal taxableAmount) {
        if (taxableAmount == null
                || taxableAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return taxableAmount
                .multiply(TAX_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTotalAmount(
            BigDecimal subtotal,
            BigDecimal discount,
            BigDecimal shipping,
            BigDecimal tax) {
        return subtotal
                .subtract(discount)
                .add(shipping)
                .add(tax)
                .setScale(2, RoundingMode.HALF_UP);
    }

}