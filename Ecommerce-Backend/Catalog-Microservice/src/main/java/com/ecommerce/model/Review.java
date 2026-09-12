package com.ecommerce.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    private Long reviewId;

    private Long productId;

    private Long userId;

    private Integer rating;

    private String title;

    private String comment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}