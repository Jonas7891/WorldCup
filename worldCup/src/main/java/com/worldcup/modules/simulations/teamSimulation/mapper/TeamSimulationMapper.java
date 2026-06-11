package com.worldcup.modules.simulations.teamSimulation.mapper;

import com.worldcup.modules.simulations.teamSimulation.dto.TeamSimulationDTO;
import com.worldcup.modules.simulations.teamSimulation.entity.TeamSimulation;
import org.springframework.stereotype.Component;

@Component
public class TeamSimulationMapper {
    public TeamSimulationDTO toDTO(TeamSimulation teamSimulation) {
        if (teamSimulation == null) {
            return null;
        }

        TeamSimulationDTO teamSimulationDTO = new TeamSimulationDTO();
        teamSimulationDTO.setId_teamSimulation(teamSimulation.getId_teamSimulation());
        teamSimulationDTO.setId_simulation(teamSimulation.getSimulation() != null ? teamSimulation.getSimulation().getId_simulation() : null);
        teamSimulationDTO.setId_team(teamSimulation.getTeam() != null ? teamSimulation.getTeam().getId_team() : null);
        teamSimulationDTO.setStatus(teamSimulation.getStatus());

        return teamSimulationDTO;
    }

    public TeamSimulation toEntity(TeamSimulationDTO teamSimulationDTO) {
        if (teamSimulationDTO == null) {
            return null;
        }

        TeamSimulation teamSimulation = new TeamSimulation();
        teamSimulation.setId_teamSimulation(teamSimulationDTO.getId_teamSimulation());
        teamSimulation.setStatus(teamSimulationDTO.getStatus() != null ? teamSimulationDTO.getStatus() : true);

        return teamSimulation;
    }
}
