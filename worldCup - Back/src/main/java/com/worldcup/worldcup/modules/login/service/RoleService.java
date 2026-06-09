package com.worldcup.worldcup.modules.login.service;

import com.worldcup.worldcup.modules.login.dto.role.RoleRequest;
import com.worldcup.worldcup.modules.login.dto.role.RoleResponse;
import com.worldcup.worldcup.shared.service.BaseService;

public interface RoleService
        extends BaseService<RoleRequest, RoleResponse, Long> {
}