package com.ecommerce.repository;

import com.ecommerce.model.Role;
import com.ecommerce.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    boolean existsByRoleType(RoleType roleType);

    Optional<Role> findByRoleType(RoleType roleType);
}
