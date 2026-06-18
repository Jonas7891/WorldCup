package com.worldcup.modules.login.dto.credential;

public record CredentialRequest(
        Long userId,
        String username,
        String password
) {
}
