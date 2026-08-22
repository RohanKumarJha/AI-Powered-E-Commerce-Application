package com.ecommerce.service.strategy;

import com.ecommerce.model.Payment;
import com.ecommerce.model.enums.PaymentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;


@Component
public class UpiStrategy implements PaymentStrategy {


    @Override
    public Payment processPayment(Payment payment) {


        payment.setPaymentStatus(
                PaymentStatus.SUCCESS
        );


        payment.setTransactionId(
                UUID.randomUUID().toString()
        );


        payment.setGatewayReferenceId(
                "UPI-" + UUID.randomUUID()
        );


        payment.setPaymentDate(
                LocalDateTime.now()
        );


        return payment;
    }
}