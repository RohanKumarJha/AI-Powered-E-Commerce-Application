package com.ecommerce.service.strategy;

import com.ecommerce.model.Payment;
import com.ecommerce.model.enums.PaymentStatus;
import org.springframework.stereotype.Component;


@Component
public class CodStrategy implements PaymentStrategy {


    @Override
    public Payment processPayment(Payment payment) {


        payment.setPaymentStatus(
                PaymentStatus.PENDING
        );


        return payment;
    }
}