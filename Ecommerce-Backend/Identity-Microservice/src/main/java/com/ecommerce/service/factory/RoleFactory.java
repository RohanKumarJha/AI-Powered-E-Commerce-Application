package com.ecommerce.service.factory;

import com.ecommerce.dto.request.RoleRequest;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.RoleMapper;
import com.ecommerce.model.Role;
import com.ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleFactory {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public Role createRole(RoleRequest request) {
        log.debug("Creating role entity for request.");
        return roleMapper.toEntity(request);
    }

    public Role getRoleById(Long roleId) {
        log.debug("Fetching role by id : {}",roleId);
        return roleRepository.findById(roleId)
                .orElseThrow(() -> {
                    log.warn("Role not found with id : {}",roleId);
                    return new ResourceNotFoundException("Role", "roleId", roleId);
                });
    }
}
