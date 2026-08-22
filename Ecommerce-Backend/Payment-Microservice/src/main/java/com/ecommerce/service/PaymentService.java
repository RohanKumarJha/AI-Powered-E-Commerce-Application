package com.ecommerce.service;

import com.ecommerce.dto.request.PaymentRequest;
import com.ecommerce.dto.request.RefundRequest;
import com.ecommerce.dto.response.PageResponse;
import com.ecommerce.dto.response.PaymentResponse;
import com.ecommerce.dto.response.PaymentStatisticsResponse;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest request);

    PaymentResponse getPaymentById(Long paymentId);

    PaymentResponse getPaymentByOrderId(Long orderId);

    PageResponse<PaymentResponse> getAllPayments(
            int page,
            int size,
            String sortBy,
            String direction
    );

    PageResponse<PaymentResponse> getMyPayments(
            int page,
            int size,
            String sortBy,
            String direction
    );

    PaymentResponse refundPayment(
            Long paymentId,
            RefundRequest request
    );

    PaymentStatisticsResponse getPaymentStatistics();

}