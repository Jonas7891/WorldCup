package com.worldcup.worldCup.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worldcup.worldCup.user.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}