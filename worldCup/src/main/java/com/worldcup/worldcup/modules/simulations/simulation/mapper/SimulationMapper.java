package com.worldcup.worldcup.modules.simulations.simulation.mapper;

import com.worldcup.worldcup.modules.simulations.simulation.dto.SimulationDTO;
import com.worldcup.worldcup.modules.simulations.simulation.entity.Simulation;
import org.springframework.stereotype.Component;

@Component
public class SimulationMapper {
    public SimulationDTO toDTO(Simulation simulation) {
        if (simulation == null) {
            return null;
        }

        SimulationDTO simulationDTO = new SimulationDTO();
        simulationDTO.setId_simulation(simulation.getId_simulation());
        simulationDTO.setName(simulation.getName());
        simulationDTO.setCreation_date(simulation.getCreation_date());
        simulationDTO.setId_user(simulation.getId_user());
        simulationDTO.setStatus(simulation.getStatus());

        return simulationDTO;
    }

    public Simulation toEntity(SimulationDTO simulationDTO) {
        if (simulationDTO == null) {
            return null;
        }

        Simulation simulation = new Simulation();
        if (simulationDTO.getId_simulation()   != null) {
            simulation.setId_simulation(simulationDTO.getId_simulation());
        } else {
            simulation.setId_simulation(null);
        }
        simulation.setName(simulationDTO.getName());
        simulation.setCreation_date(simulationDTO.getCreation_date());
        simulation.setId_user(simulationDTO.getId_user());
        simulation.setStatus(simulationDTO.getStatus());

        return simulation;
    }
}
