package com.worldcup.modules.login.repository;

import com.worldcup.modules.login.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
}
