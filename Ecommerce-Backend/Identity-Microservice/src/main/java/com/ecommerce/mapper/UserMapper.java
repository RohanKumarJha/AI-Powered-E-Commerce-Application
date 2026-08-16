package com.ecommerce.mapper;

import com.ecommerce.dto.request.RegisterRequest;
import com.ecommerce.dto.request.UpdateUserRequest;
import com.ecommerce.dto.response.UserResponse;
import com.ecommerce.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public User toEntity(RegisterRequest request) {
        return modelMapper.map(request, User.class);
    }

    public UserResponse toResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    public void updateEntity(UpdateUserRequest request, User user) {
        modelMapper.map(request, user);
    }
}
