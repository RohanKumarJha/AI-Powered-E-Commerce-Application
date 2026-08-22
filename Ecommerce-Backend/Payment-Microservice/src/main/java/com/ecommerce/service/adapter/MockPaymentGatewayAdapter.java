package com.ecommerce.service.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;


@Component
@Slf4j
public class MockPaymentGatewayAdapter implements PaymentGatewayAdapter {


    @Override
    public String processPayment(BigDecimal amount) {
        log.info("Processing payment through mock payment gateway. Amount: {}", amount);
        String transactionReference = "TXN-" + UUID.randomUUID();
        log.info("Payment processed successfully. Gateway Reference: {}", transactionReference);
        return transactionReference;
    }



    @Override
    public String refundPayment(
            String transactionId,
            BigDecimal amount
    ) {
        log.info("Processing refund through mock payment gateway. Transaction ID: {}, Amount: {}",
                transactionId,
                amount);
        String refundReference = "REFUND-" + UUID.randomUUID();
        log.info("Refund processed successfully. Refund Reference: {}", refundReference);
        return refundReference;
    }

}