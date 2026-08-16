package com.ecommerce.service;

import com.ecommerce.dto.request.RegisterRequest;
import com.ecommerce.dto.request.UpdateUserRequest;
import com.ecommerce.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse registerUser(RegisterRequest request);

    UserResponse getUserById(Long userId);

    UserResponse getUserByEmail(String email);

    UserResponse updateUser(Long userId, UpdateUserRequest request);

    void deleteUser(Long userId);

    List<UserResponse> getAllUsers();

}