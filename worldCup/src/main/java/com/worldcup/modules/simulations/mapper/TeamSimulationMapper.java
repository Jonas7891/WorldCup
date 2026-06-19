package com.worldcup.modules.simulations.mapper;

import com.worldcup.modules.simulations.dto.TeamSimulationDTO;
import com.worldcup.modules.simulations.entity.TeamSimulation;
import org.springframework.stereotype.Component;

@Component
public class TeamSimulationMapper {
    public TeamSimulationDTO toDTO(TeamSimulation teamSimulation) {
        if (teamSimulation == null) {
            return null;
        }

        TeamSimulationDTO teamSimulationDTO = new TeamSimulationDTO();
        teamSimulationDTO.setId_teamSimulation(teamSimulation.getIdTeamSimulation());
        teamSimulationDTO.setId_simulation(teamSimulation.getSimulation() != null ? teamSimulation.getSimulation().getId_simulation() : null);
        teamSimulationDTO.setId_team(teamSimulation.getTeam() != null ? teamSimulation.getTeam().getIdTeam() : null);
        teamSimulationDTO.setStatus(teamSimulation.getStatus());

        return teamSimulationDTO;
    }

    public TeamSimulation toEntity(TeamSimulationDTO teamSimulationDTO) {
        if (teamSimulationDTO == null) {
            return null;
        }

        TeamSimulation teamSimulation = new TeamSimulation();
        teamSimulation.setIdTeamSimulation(teamSimulationDTO.getId_teamSimulation());
        teamSimulation.setStatus(teamSimulationDTO.getStatus() != null ? teamSimulationDTO.getStatus() : true);

        return teamSimulation;
    }
}
