package com.ecommerce.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {

    private Long reviewId;

    private Long userId;

    private Integer rating;

    private String title;

    private String comment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}