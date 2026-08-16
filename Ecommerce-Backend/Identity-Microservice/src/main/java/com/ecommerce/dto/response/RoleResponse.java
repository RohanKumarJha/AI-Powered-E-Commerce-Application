package com.ecommerce.dto.response;

import com.ecommerce.model.enums.RoleType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse {

    private Long roleId;

    private RoleType roleType;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}