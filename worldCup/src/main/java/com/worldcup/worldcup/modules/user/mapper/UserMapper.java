package com.worldcup.worldcup.modules.user.mapper;

import com.worldcup.worldcup.modules.role.dto.RoleRequest;
import com.worldcup.worldcup.modules.user.dto.UserRequest;
import com.worldcup.worldcup.modules.user.dto.UserResponse;
import com.worldcup.worldcup.modules.user.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper {
    AppUser toEntity(UserRequest request);

    UserResponse toResponse(AppUser entity);
}
