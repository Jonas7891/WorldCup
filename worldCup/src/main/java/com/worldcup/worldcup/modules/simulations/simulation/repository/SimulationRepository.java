package com.worldcup.worldcup.modules.simulations.simulation.repository;

import com.worldcup.worldcup.modules.simulations.simulation.entity.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SimulationRepository extends JpaRepository<Simulation, Integer>{

	// Find only active teams (status = true)
	java.util.List<Simulation> findByStatusTrue();

	// Check if team with given name already exists
	@Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM team t WHERE t.name = :name")
	boolean existsByName(@Param("name") String name);

}
