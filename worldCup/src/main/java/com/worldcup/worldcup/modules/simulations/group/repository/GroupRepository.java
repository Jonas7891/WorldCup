package com.worldcup.worldCup.modules.simulations.group.repository;

import com.worldcup.worldCup.modules.simulations.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Group, Integer>{

	java.util.List<Group> findByStatus(Boolean status);

	@Query("SELECT g FROM group g WHERE g.simulation.id_simulation = :simulation_id")
	java.util.List<Group> findBySimulationId(@Param("simulation_id") Integer simulation_id);

	@Query("SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END FROM group g WHERE g.name = :name")
	boolean existsByName(@Param("name") String name);

}
