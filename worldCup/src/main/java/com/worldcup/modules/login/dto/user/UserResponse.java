package com.worldcup.modules.login.dto.user;

import java.time.LocalDateTime;

public record UserResponse(
        String firstName,
        String lastName,
        String mail,
        LocalDateTime register
) {
}
