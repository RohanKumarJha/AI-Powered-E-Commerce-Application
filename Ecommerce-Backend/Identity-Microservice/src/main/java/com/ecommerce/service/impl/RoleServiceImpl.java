package com.ecommerce.service.impl;

import com.ecommerce.dto.request.RoleRequest;
import com.ecommerce.dto.response.RoleResponse;
import com.ecommerce.exception.ResourceAlreadyExistsException;
import com.ecommerce.mapper.RoleMapper;
import com.ecommerce.model.Role;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.service.RoleService;
import com.ecommerce.service.factory.RoleFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleFactory roleFactory;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponse createRole(RoleRequest request) {
        if (roleRepository.existsByRoleType(request.getRoleType())) {
            throw new ResourceAlreadyExistsException("Role", "roleType", request.getRoleType());
        }
        Role role = roleFactory.createRole(request);
        Role savedRole = roleRepository.save(role);
        log.info("Create role successfully with roleId : {}",savedRole.getRoleId());
        return roleMapper.toResponse(savedRole);
    }

    @Override
    public RoleResponse updateRole(Long roleId, RoleRequest request) {
        Role role = roleFactory.getRoleById(roleId);
        if (!role.getRoleType().equals(request.getRoleType())
                && roleRepository.existsByRoleType(request.getRoleType())) {
            throw new ResourceAlreadyExistsException(
                    "Role",
                    "roleType",
                    request.getRoleType()
            );
        }
        role.setRoleType(request.getRoleType());
        role.setDescription(request.getDescription());
        Role savedRole = roleRepository.save(role);
        log.info("Updated role successfully with roleId : {}", roleId);
        return roleMapper.toResponse(savedRole);
    }

    @Override
    public void deleteRole(Long roleId) {
        Role role = roleFactory.getRoleById(roleId);
        roleRepository.delete(role);
        log.info("Deleted role successfully with roleId : {}",roleId);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<RoleResponse> roleResponses = roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponse)
                .toList();
        log.info("Get all roles successfully");
        return roleResponses;
    }

    @Override
    public RoleResponse getRoleById(Long roleId) {
        Role getRoleById = roleFactory.getRoleById(roleId);
        log.info("Get role successfully with roleId : {}",roleId);
        return roleMapper.toResponse(getRoleById);
    }
}
