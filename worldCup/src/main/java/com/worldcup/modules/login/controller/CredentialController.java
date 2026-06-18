package com.worldcup.modules.login.controller;

import com.worldcup.modules.login.dto.credential.CredentialRequest;
import com.worldcup.modules.login.dto.credential.CredentialResponse;
import com.worldcup.modules.login.service.CredentialService;
import com.worldcup.shared.controller.BaseController;
import com.worldcup.shared.service.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/credential")
public class CredentialController
        extends BaseController<CredentialRequest, CredentialResponse, Long> {
    private final CredentialService service;

    @Override
    protected BaseService<CredentialRequest, CredentialResponse, Long> getService(){
        return service;
    }
}
