package com.worldcup.modules.login.controller;

import com.worldcup.modules.login.dto.user.UserRequest;
import com.worldcup.modules.login.dto.user.UserResponse;
import com.worldcup.modules.login.service.UserService;
import com.worldcup.shared.controller.BaseController;
import com.worldcup.shared.service.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController
        extends BaseController<UserRequest, UserResponse, Long> {

    private final UserService service;

    @Override
    protected BaseService<UserRequest, UserResponse, Long> getService() {
        return service;
    }
}
