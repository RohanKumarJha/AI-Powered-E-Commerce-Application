package com.ecommerce.mapper;

import com.ecommerce.dto.request.RoleRequest;
import com.ecommerce.dto.response.RoleResponse;
import com.ecommerce.model.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    private final ModelMapper modelMapper;

    public Role toEntity(RoleRequest request) {
        return modelMapper.map(request, Role.class);
    }

    public RoleResponse toResponse(Role role) {
        return modelMapper.map(role, RoleResponse.class);
    }
}
