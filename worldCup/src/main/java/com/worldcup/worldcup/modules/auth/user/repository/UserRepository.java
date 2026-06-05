package com.worldcup.worldcup.modules.auth.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worldcup.worldcup.modules.auth.user.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}