package com.ecommerce.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    private Long categoryId;

    private String name;

    private String description;

    // Self Reference
    private Long parentCategoryId;

    private String imageUrl;

    @Builder.Default
    private Boolean active = true;

    @Builder.Default
    private Set<Long> productIds = new HashSet<>();

    private Long createdBy;

    private Long updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}