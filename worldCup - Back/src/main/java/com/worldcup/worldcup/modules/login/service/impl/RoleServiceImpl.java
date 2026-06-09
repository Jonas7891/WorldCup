package com.worldcup.worldcup.modules.login.service.impl;

import com.worldcup.worldcup.modules.login.dto.role.RoleRequest;
import com.worldcup.worldcup.modules.login.dto.role.RoleResponse;
import com.worldcup.worldcup.modules.login.entity.Role;
import com.worldcup.worldcup.modules.login.mapper.RoleMapper;
import com.worldcup.worldcup.modules.login.repository.RoleRepository;
import com.worldcup.worldcup.modules.login.service.RoleService;
import com.worldcup.worldcup.shared.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl
        extends BaseServiceImpl<Role, RoleRequest, RoleResponse, Long>
        implements RoleService {

    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
        super(
                repository,
                mapper::toResponse,
                mapper::toEntity,
                mapper::updateEntity,
                id -> new RuntimeException("Role not found with id: " + id)
        );
    }
}