package com.worldcup.worldcup.modules.login.service;

import com.worldcup.worldcup.modules.login.dto.credential.CredentialRequest;
import com.worldcup.worldcup.modules.login.dto.credential.CredentialResponse;
import com.worldcup.worldcup.shared.service.BaseService;

public interface CredentialService
        extends BaseService<CredentialRequest, CredentialResponse, Long> {
}
