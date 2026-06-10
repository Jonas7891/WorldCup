package com.worldcup.worldcup.modules.simulations.simulation.validator;

import com.worldcup.worldcup.modules.simulations.simulation.dto.SimulationDTO;
import org.springframework.stereotype.Component;

@Component
public class SimulationValidator {

    public boolean validateSimulationDTO(SimulationDTO simulationDTO) {
        if (simulationDTO == null) {
            return false;
        }
        if (simulationDTO.getName() == null || simulationDTO.getName().isEmpty() || simulationDTO.getName().length() > 100) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío y/o superar un 1 carácter.");
        }
        if (simulationDTO.getCreation_date() == null) {
            throw new IllegalArgumentException("La fecha de creación no puede ser nula.");
        }
        if (simulationDTO.getId_user() == null || simulationDTO.getId_user() <= 0) {
            throw new IllegalArgumentException("El identificador del usuario no puede ser nulo o menor o igual a cero.");
        }
        return true;
    }

    public void validateSimulationId(Integer simulationId) {
        if (simulationId == null || simulationId <= 0) {
            throw new IllegalArgumentException("El identificador del grupo debe ser mayor que cero.");
        }
    }
}
