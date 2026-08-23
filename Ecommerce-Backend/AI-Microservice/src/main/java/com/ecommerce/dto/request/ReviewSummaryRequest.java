package com.ecommerce.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSummaryRequest {

    @NotEmpty(message = "Reviews cannot be empty.")
    private List<String> reviews;

}