package com.worldcup.modules.login.service.impl;

import com.worldcup.modules.login.dto.credential.CredentialRequest;
import com.worldcup.modules.login.dto.credential.CredentialResponse;
import com.worldcup.modules.login.entity.Credential;
import com.worldcup.modules.login.mapper.CredentialMapper;
import com.worldcup.modules.login.repository.CredentialRepository;
import com.worldcup.modules.login.service.CredentialService;
import com.worldcup.shared.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CredentialServiceImpl
        extends BaseServiceImpl<Credential, CredentialRequest, CredentialResponse, Long>
        implements CredentialService {
    public CredentialServiceImpl(CredentialRepository repository, CredentialMapper mapper) {
        super(
                repository,
                mapper::toResponse,
                mapper::toEntity,
                mapper::updateEntity,
                id -> new RuntimeException("Credential not found with id: " + id)
        );
    }
}
