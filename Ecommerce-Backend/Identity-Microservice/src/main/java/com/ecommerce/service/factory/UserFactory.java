package com.ecommerce.service.factory;

import com.ecommerce.dto.request.RegisterRequest;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.UserMapper;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFactory {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public User createUser(RegisterRequest request) {
        log.debug("Creating user entity for request");
        return userMapper.toEntity(request);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.warn("User not found with userId : {}",
                            userId);
                    return new ResourceNotFoundException("User", "userId", userId);
                });
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("User not found with email : {}",
                            email);
                    return new ResourceNotFoundException("User", "email", email);
                });
    }
}
