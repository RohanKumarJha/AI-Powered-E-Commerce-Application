package com.ecommerce.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentStatisticsResponse {

    private long totalPayments;

    private long successfulPayments;

    private long pendingPayments;

    private long failedPayments;

    private long refundedPayments;

    private BigDecimal totalRevenue;

    private BigDecimal totalRefundAmount;

}