package com.worldcup.modules.login.service;

import com.worldcup.modules.login.dto.role.RoleRequest;
import com.worldcup.modules.login.dto.role.RoleResponse;
import com.worldcup.shared.service.BaseService;

public interface RoleService
        extends BaseService<RoleRequest, RoleResponse, Long> {
}