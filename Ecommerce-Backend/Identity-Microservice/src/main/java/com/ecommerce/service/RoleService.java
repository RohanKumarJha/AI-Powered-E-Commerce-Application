package com.ecommerce.service;

import com.ecommerce.dto.request.RoleRequest;
import com.ecommerce.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse createRole(RoleRequest request);

    RoleResponse updateRole(Long roleId, RoleRequest request);

    void deleteRole(Long roleId);

    List<RoleResponse> getAllRoles();

    RoleResponse getRoleById(Long roleId);

}