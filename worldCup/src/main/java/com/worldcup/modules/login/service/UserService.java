package com.worldcup.worldcup.modules.login.service;

import com.worldcup.worldcup.modules.login.dto.user.UserRequest;
import com.worldcup.worldcup.modules.login.dto.user.UserResponse;
import com.worldcup.worldcup.shared.service.BaseService;

public interface UserService
        extends BaseService<UserRequest, UserResponse, Long> {
}

