package com.ecommerce.dto.request;

import com.ecommerce.model.enums.CartStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCartStatusRequest {

    @NotNull(message = "Cart status is required")
    private CartStatus status;
}