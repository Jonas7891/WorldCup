package com.worldcup.worldcup.modules.auth.role.service;

import com.worldcup.worldcup.modules.auth.role.dto.RoleRequest;
import com.worldcup.worldcup.modules.auth.role.dto.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse findById(Long id);

    List<RoleResponse> findAll();

    RoleResponse save(RoleRequest request);

    RoleResponse update (Long id, RoleRequest request);

    void deleteById(Long id);
}
