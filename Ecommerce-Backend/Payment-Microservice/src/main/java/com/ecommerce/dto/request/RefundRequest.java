package com.ecommerce.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefundRequest {


    @NotNull(message = "Payment id is required")
    private Long paymentId;


    @NotNull(message = "Refund amount is required")
    @DecimalMin(
            value = "0.01",
            message = "Refund amount must be greater than zero"
    )
    private BigDecimal refundAmount;


    @NotBlank(message = "Refund reason is required")
    @Size(
            max = 500,
            message = "Refund reason cannot exceed 500 characters"
    )
    private String reason;

}