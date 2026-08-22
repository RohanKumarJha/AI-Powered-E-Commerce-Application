package com.ecommerce.dto.response;

import com.ecommerce.model.enums.PaymentMethod;
import com.ecommerce.model.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {


    private Long paymentId;


    private Long orderId;


    private Long userId;


    private PaymentMethod paymentMethod;


    private PaymentStatus paymentStatus;


    private BigDecimal amount;


    private String currency;


    private String transactionId;


    private String gatewayReferenceId;


    private String refundId;


    private BigDecimal refundAmount;


    private LocalDateTime paymentDate;


    private LocalDateTime refundDate;


    private String failureReason;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;

}