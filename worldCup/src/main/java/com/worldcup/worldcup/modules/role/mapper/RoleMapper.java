package com.worldcup.worldcup.modules.role.mapper;

import com.worldcup.worldcup.modules.role.dto.RoleRequest;
import com.worldcup.worldcup.modules.role.dto.RoleResponse;
import com.worldcup.worldcup.modules.role.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel= "spring")
public interface RoleMapper {
    Role toEntity(RoleRequest request);

    RoleResponse toResponse(Role entity);
}
