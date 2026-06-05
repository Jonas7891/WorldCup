package com.worldcup.worldcup.modules.auth.role.repository;

import com.worldcup.worldcup.modules.auth.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
