package com.worldcup.worldcup.modules.auth.role.dto;

public record RoleRequest(
        String role,
        String description,
        boolean status
) {
}
