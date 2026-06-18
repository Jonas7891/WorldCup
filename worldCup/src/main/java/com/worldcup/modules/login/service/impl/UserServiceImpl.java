package com.worldcup.modules.login.service.impl;

import com.worldcup.modules.login.dto.user.UserRequest;
import com.worldcup.modules.login.dto.user.UserResponse;
import com.worldcup.modules.login.entity.AppUser;
import com.worldcup.modules.login.mapper.UserMapper;
import com.worldcup.modules.login.repository.UserRepository;
import com.worldcup.modules.login.service.UserService;
import com.worldcup.shared.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        extends BaseServiceImpl<AppUser, UserRequest, UserResponse, Long>
        implements UserService {

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        super(
                repository,
                mapper::toResponse,
                mapper::toEntity,
                mapper::updateEntity,
                id -> new RuntimeException("User not found with id: " + id)
        );
    }
}