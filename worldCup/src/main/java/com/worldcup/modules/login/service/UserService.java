package com.worldcup.modules.login.service;

import com.worldcup.modules.login.dto.user.UserRequest;
import com.worldcup.modules.login.dto.user.UserResponse;
import com.worldcup.shared.service.BaseService;

public interface UserService
        extends BaseService<UserRequest, UserResponse, Long> {
}

