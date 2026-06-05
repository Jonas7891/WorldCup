package com.worldcup.worldcup.modules.auth.user.dto;

import java.time.LocalDateTime;

public record UserResponse(
        String firstName,
        String lastName,
        String mail,
        LocalDateTime register
) {
}
