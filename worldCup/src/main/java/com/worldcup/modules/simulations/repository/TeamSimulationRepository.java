package com.worldcup.modules.simulations.repository;

import com.worldcup.modules.simulations.entity.TeamSimulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamSimulationRepository extends JpaRepository<TeamSimulation, Integer>{

	List<TeamSimulation> findByStatusTrue();

	@Query(value = "SELECT ts FROM TeamSimulation ts WHERE ts.simulation.id_simulation = :simulationId AND ts.team.id_team = :teamId", nativeQuery = true)
	List<TeamSimulation> findBySimulationIdAndTeamId(@Param("simulationId") Integer simulationId, @Param("teamId") Integer teamId);

	@Query(value = "SELECT ts FROM TeamSimulation ts WHERE ts.simulation.id_simulation = :simulationId", nativeQuery = true)
	List<TeamSimulation> findBySimulationId(@Param("simulationId") Integer simulationId);

	@Query(value = "SELECT ts FROM TeamSimulation ts WHERE ts.team.id_team = :teamId", nativeQuery = true)
	List<TeamSimulation> findByTeamId(@Param("teamId") Integer teamId);
}
