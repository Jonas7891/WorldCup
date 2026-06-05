package com.worldcup.worldcup.modules.auth.role.mapper;

import com.worldcup.worldcup.modules.auth.role.dto.RoleRequest;
import com.worldcup.worldcup.modules.auth.role.dto.RoleResponse;
import com.worldcup.worldcup.modules.auth.role.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel= "spring")
public interface RoleMapper {
    Role toEntity(RoleRequest request);

    RoleResponse toResponse(Role entity);

    Role toDomain(RoleRequest request);
}
