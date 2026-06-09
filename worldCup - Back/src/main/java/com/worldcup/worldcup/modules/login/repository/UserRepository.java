package com.worldcup.worldcup.modules.login.repository;

import com.worldcup.worldcup.modules.login.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}