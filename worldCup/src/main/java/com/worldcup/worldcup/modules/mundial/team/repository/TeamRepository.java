package com.worldcup.worldcup.modules.mundial.team.repository;

import com.worldcup.worldcup.modules.mundial.team.entity.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer>{

	// Find only active teams (status = true)
	java.util.List<Team> findByStatusTrue();

	// Check if team with given name already exists
	@Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM team t WHERE t.name = :name")
	boolean existsByName(@Param("name") String name);

}
