package com.worldcup.worldCup.modules.simulations.groups.repository;

import com.worldcup.worldCup.modules.simulations.groups.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupsRepository extends JpaRepository<Groups, Integer>{

	java.util.List<Groups> findByStatus(Boolean status);

	// The entity is declared with @Entity(name = "groups"), so use 'groups' as the JPQL root
	@Query("SELECT g FROM groups g WHERE g.simulation.id_simulation = :simulation_id")
	java.util.List<Groups> findBySimulationId(@Param("simulation_id") Integer simulation_id);

	@Query("SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END FROM groups g WHERE g.name = :name")
	boolean existsByName(@Param("name") String name);

}
