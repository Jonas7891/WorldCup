package com.worldcup.worldcup.modules.login.repository;

import com.worldcup.worldcup.modules.login.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}