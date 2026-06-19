package com.worldcup.modules.mundial.repository;

import com.worldcup.modules.mundial.entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhaseRepository extends JpaRepository<Phase, Integer>{

	// Find only active teams (status = true)
	java.util.List<Phase> findByStatusTrue();

	// Check if team with given name already exists
	// Check if phase with given name already exists
	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM phase p WHERE p.name = :name")
	boolean existsByName(@Param("name") String name);

}
