package com.worldcup.worldcup.modules.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worldcup.worldcup.modules.user.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}