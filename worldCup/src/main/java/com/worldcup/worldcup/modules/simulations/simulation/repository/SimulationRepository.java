package com.worldcup.worldCup.modules.simulations.simulation.repository;

import com.worldcup.worldCup.modules.simulations.simulation.entity.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SimulationRepository extends JpaRepository<Simulation, Integer>{

	java.util.List<Simulation> findByStatusIn(java.util.List<com.worldcup.worldCup.modules.simulations.simulation.entity.EnumSimulation> statuses);

	@Query("SELECT s FROM simulation s WHERE s.id_user = :user_id")
	java.util.List<Simulation> findByUserId(@Param("user_id") Integer user_id);

	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM simulation s WHERE s.name = :name")
	boolean existsByName(@Param("name") String name);

}
