package com.worldcup.modules.login.repository;

import com.worldcup.modules.login.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}