package com.worldcup.modules.login.controller;


import com.worldcup.modules.login.dto.role.RoleRequest;
import com.worldcup.modules.login.dto.role.RoleResponse;
import com.worldcup.modules.login.service.RoleService;
import com.worldcup.shared.controller.BaseController;
import com.worldcup.shared.service.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/role")
public class RoleController
        extends BaseController<RoleRequest, RoleResponse, Long> {

    private final RoleService service;

    @Override
    protected BaseService<RoleRequest, RoleResponse, Long> getService() {
        return service;
    }
}