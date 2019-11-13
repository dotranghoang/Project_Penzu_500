package com.codegym.penzuproject.service;

import com.codegym.penzuproject.model.Role;
import com.codegym.penzuproject.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName roleName);
}
