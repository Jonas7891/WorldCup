package com.worldcup.modules.login.dto.user;

public record UserRequest(
        String firstName,
        String lastName,
        String mail
) {
}
