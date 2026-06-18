package com.worldcup.modules.login.repository;

import com.worldcup.modules.login.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}