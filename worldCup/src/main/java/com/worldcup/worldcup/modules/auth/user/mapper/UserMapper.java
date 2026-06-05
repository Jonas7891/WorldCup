package com.worldcup.worldcup.modules.auth.user.mapper;

import com.worldcup.worldcup.modules.auth.user.dto.UserRequest;
import com.worldcup.worldcup.modules.auth.user.dto.UserResponse;
import com.worldcup.worldcup.modules.auth.user.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper {
    AppUser toEntity(UserRequest request);

    UserResponse toResponse(AppUser entity);
}
