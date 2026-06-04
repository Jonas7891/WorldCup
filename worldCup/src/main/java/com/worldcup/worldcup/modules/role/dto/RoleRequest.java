package com.worldcup.worldcup.modules.role.dto;

public record RoleRequest(
        String role,
        String description,
        boolean status
) {
}
