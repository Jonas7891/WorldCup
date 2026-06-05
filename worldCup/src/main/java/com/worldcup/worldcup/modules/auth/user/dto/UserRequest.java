package com.worldcup.worldcup.modules.auth.user.dto;

public record UserRequest(
        String firstName,
        String lastName,
        String mail
) {
}
