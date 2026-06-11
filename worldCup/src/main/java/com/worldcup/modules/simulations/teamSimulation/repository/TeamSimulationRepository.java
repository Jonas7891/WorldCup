package com.worldcup.modules.simulations.teamSimulation.repository;

import com.worldcup.modules.simulations.teamSimulation.entity.TeamSimulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamSimulationRepository extends JpaRepository<TeamSimulation, Integer>{

	List<TeamSimulation> findByStatusTrue();

	@Query("SELECT ts FROM team_simulation ts WHERE ts.simulation.id_simulation = :simulationId AND ts.team.id_team = :teamId")
	List<TeamSimulation> findBySimulationIdAndTeamId(@Param("simulationId") Integer simulationId, @Param("teamId") Integer teamId);

	@Query("SELECT ts FROM team_simulation ts WHERE ts.simulation.id_simulation = :simulationId")
	List<TeamSimulation> findBySimulationId(@Param("simulationId") Integer simulationId);

	@Query("SELECT ts FROM team_simulation ts WHERE ts.team.id_team = :teamId")
	List<TeamSimulation> findByTeamId(@Param("teamId") Integer teamId);
}
