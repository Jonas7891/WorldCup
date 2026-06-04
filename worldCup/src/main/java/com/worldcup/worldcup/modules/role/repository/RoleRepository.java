package com.worldcup.worldcup.modules.role.repository;

import com.worldcup.worldcup.modules.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
