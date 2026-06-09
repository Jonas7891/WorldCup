package com.worldcup.worldcup.modules.login.mapper;


import com.worldcup.worldcup.modules.login.dto.credential.CredentialRequest;
import com.worldcup.worldcup.modules.login.dto.credential.CredentialResponse;
import com.worldcup.worldcup.modules.login.entity.Credential;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel= "spring")
public interface CredentialMapper {
    Credential toEntity(CredentialRequest request);

    CredentialResponse toResponse(Credential entity);

    void updateEntity(CredentialRequest update, @MappingTarget Credential entity);
}
