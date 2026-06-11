package com.worldcup.worldcup.modules.login.mapper;

import com.worldcup.worldcup.modules.login.dto.role.RoleRequest;
import com.worldcup.worldcup.modules.login.dto.role.RoleResponse;
import com.worldcup.worldcup.modules.login.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel= "spring")
public interface RoleMapper {
    Role toEntity(RoleRequest request);

    RoleResponse toResponse(Role entity);

    void updateEntity(RoleRequest update, @MappingTarget Role entity);
}
