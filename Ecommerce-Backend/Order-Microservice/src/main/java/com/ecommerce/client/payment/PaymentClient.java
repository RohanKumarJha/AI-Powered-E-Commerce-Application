package com.ecommerce.client.payment;

import java.math.BigDecimal;

public interface PaymentClient {

    Long createPayment(Long orderId, BigDecimal amount);

}