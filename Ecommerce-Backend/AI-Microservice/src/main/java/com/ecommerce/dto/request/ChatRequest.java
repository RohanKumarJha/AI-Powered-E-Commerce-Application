package com.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRequest {

    @NotNull(message = "User ID is required.")
    @Positive(message = "User ID must be greater than zero.")
    private Long userId;

    @NotBlank(message = "Message is required.")
    private String message;

}