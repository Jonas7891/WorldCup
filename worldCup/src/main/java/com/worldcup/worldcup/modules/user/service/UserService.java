package com.worldcup.worldcup.modules.user.service;


import com.worldcup.worldcup.modules.user.dto.UserRequest;
import com.worldcup.worldcup.modules.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse findById(Long id);

    List<UserResponse> findAll();

    UserResponse save(UserRequest request);

    UserResponse update (Long id, UserRequest request);

    void deleteById(Long id);
}