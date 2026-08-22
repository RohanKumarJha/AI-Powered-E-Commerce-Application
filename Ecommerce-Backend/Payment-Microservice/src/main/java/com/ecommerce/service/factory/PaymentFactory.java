package com.ecommerce.service.factory;

import com.ecommerce.client.order.OrderClient;
import com.ecommerce.client.order.response.OrderResponse;
import com.ecommerce.dto.request.PaymentRequest;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.mapper.PaymentMapper;
import com.ecommerce.model.enums.PaymentStatus;
import com.ecommerce.model.Payment;
import com.ecommerce.security.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentFactory {

    private final PaymentMapper paymentMapper;
    private final OrderClient orderClient;

    public Payment createPayment(PaymentRequest request) {
        log.info("Creating payment entity for order ID: {}", request.getOrderId());
        log.info("Fetching order details for order ID: {}", request.getOrderId());
        OrderResponse order = orderClient.getOrderById(
                request.getOrderId()
        );
        if (order == null) {
            throw new BadRequestException(
                    "Order not found with id: " + request.getOrderId()
            );
        }
        Payment payment = paymentMapper.toEntity(request);
        payment.setUserId(
                UserContext.getCurrentUserId()
        );
        payment.setCurrency("INR");
        payment.setPaymentStatus(
                PaymentStatus.PENDING
        );
        payment.setCreatedAt(
                LocalDateTime.now()
        );
        payment.setUpdatedAt(
                LocalDateTime.now()
        );
        log.info("Payment entity created successfully for order ID: {} and user ID: {}",
                request.getOrderId(),UserContext.getCurrentUserId()
                );
        return payment;
    }
}