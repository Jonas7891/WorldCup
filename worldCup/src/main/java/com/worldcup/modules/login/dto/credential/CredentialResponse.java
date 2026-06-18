package com.worldcup.modules.login.dto.credential;

import com.worldcup.modules.login.dto.user.UserResponse;

import java.time.LocalDateTime;

public record CredentialResponse(
        UserResponse user,
        String username,
        String password,
        LocalDateTime lastAccess
) {
}
