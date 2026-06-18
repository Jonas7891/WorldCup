package com.worldcup.modules.login.service;

import com.worldcup.modules.login.dto.credential.CredentialRequest;
import com.worldcup.modules.login.dto.credential.CredentialResponse;
import com.worldcup.shared.service.BaseService;

public interface CredentialService
        extends BaseService<CredentialRequest, CredentialResponse, Long> {
}
