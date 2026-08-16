package com.ecommerce.dto.request;

import com.ecommerce.model.enums.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequest {

    @NotBlank(message = "Full name is required")
    @Size(
            max = 50,
            message = "Full name cannot exceed 50 characters"
    )
    private String fullName;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Mobile number must be a valid 10-digit Indian mobile number"
    )
    private String mobileNumber;

    @NotBlank(message = "Address Line 1 is required")
    @Size(
            max = 255,
            message = "Address Line 1 cannot exceed 255 characters"
    )
    private String addressLine1;

    @Size(
            max = 255,
            message = "Address Line 2 cannot exceed 255 characters"
    )
    private String addressLine2;

    @NotBlank(message = "City is required")
    @Size(
            max = 100,
            message = "City cannot exceed 100 characters"
    )
    private String city;

    @NotBlank(message = "State is required")
    @Size(
            max = 100,
            message = "State cannot exceed 100 characters"
    )
    private String state;

    @NotBlank(message = "Country is required")
    @Size(
            max = 100,
            message = "Country cannot exceed 100 characters"
    )
    private String country;

    @NotBlank(message = "Postal code is required")
    @Pattern(
            regexp = "^[1-9][0-9]{5}$",
            message = "Postal code must be a valid 6-digit Indian PIN code"
    )
    private String postalCode;

    @NotNull(message = "Address type is required")
    private AddressType addressType;

    @Builder.Default
    private Boolean isDefault = false;
}