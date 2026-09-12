package com.ecommerce.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand {

    @Id
    private Long brandId;

    private String name;

    private String description;

    private String logoUrl;

    private String website;

    @Builder.Default
    private Boolean active = true;

    @Builder.Default
    private Set<Long> productIds = new HashSet<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long createdBy;

    private Long updatedBy;
}