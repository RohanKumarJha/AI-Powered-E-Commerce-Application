package com.ecommerce.client.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class MockPaymentClient implements PaymentClient {

    @Override
    public Long createPayment(Long orderId, BigDecimal amount) {
        log.info(
                "Creating mock payment for orderId: {} with amount: {}",
                orderId,
                amount
        );
        Long paymentId = 1001L;
        log.debug(
                "Returning mock paymentId: {} for orderId: {}",
                paymentId,
                orderId
        );
        return paymentId;
    }
}