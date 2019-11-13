package com.codegym.penzuproject.repository;

import com.codegym.penzuproject.model.Role;
import com.codegym.penzuproject.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
