package com.worldcup.worldcup.modules.login.dto.user;

public record UserRequest(
        String firstName,
        String lastName,
        String mail
) {
}
