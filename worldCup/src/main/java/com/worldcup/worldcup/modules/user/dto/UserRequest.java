package com.worldcup.worldcup.modules.user.dto;

public record UserRequest(
        String firstName,
        String lastName,
        String mail
) {
}
