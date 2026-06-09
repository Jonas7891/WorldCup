package com.worldcup.worldcup.modules.login.mapper;

import com.worldcup.worldcup.modules.login.dto.user.UserRequest;
import com.worldcup.worldcup.modules.login.dto.user.UserResponse;
import com.worldcup.worldcup.modules.login.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface UserMapper {
    AppUser toEntity(UserRequest request);

    UserResponse toResponse(AppUser entity);

    void updateEntity(UserRequest update, @MappingTarget AppUser entity);
}
