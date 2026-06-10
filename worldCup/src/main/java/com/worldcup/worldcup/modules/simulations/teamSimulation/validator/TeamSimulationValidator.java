package com.worldcup.worldcup.modules.simulations.teamSimulation.validator;

import com.worldcup.worldcup.modules.simulations.teamSimulation.dto.TeamSimulationDTO;
import org.springframework.stereotype.Component;

@Component
public class TeamSimulationValidator {

    public boolean validateTeamSimulationDTO(TeamSimulationDTO teamSimulationDTO) {
        if (teamSimulationDTO == null) {
            return false;
        }
        if (teamSimulationDTO.getId_simulation() == null || teamSimulationDTO.getId_simulation() <= 0) {
            throw new IllegalArgumentException("El identificador de la simulación no en simulación debe ser mayor que cero.");
        }
        if (teamSimulationDTO.getId_team() == null || teamSimulationDTO.getId_team() <= 0) {
            throw new IllegalArgumentException("El identificador del equipo en simulación debe ser mayor que cero.");
        }

        return true;
    }

    public void validateTeamSimulationId(Integer teamSimulationId) {
        if (teamSimulationId == null || teamSimulationId <= 0) {
            throw new IllegalArgumentException("El identificador del equipo en simulación debe ser mayor que cero.");
        }
    }
}
