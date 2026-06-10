package com.worldcup.worldCup.modules.simulations.simulation.services.implement;

import com.worldcup.worldCup.modules.simulations.simulation.dto.SimulationDTO;
import com.worldcup.worldCup.modules.simulations.simulation.entity.EnumSimulation;
import com.worldcup.worldCup.modules.simulations.simulation.entity.Simulation;
import com.worldcup.worldCup.modules.simulations.simulation.mapper.SimulationMapper;
import com.worldcup.worldCup.modules.simulations.simulation.repository.SimulationRepository;
import com.worldcup.worldCup.modules.simulations.simulation.services.interfaces.ISimulation;
import com.worldcup.worldCup.modules.simulations.simulation.validator.SimulationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SimulationImplement implements ISimulation {
    @Autowired
    private SimulationRepository simulationRepository;

    @Autowired
    private SimulationMapper simulationMapper;

    @Autowired
    private SimulationValidator simulationValidator;

    @Override
    public String Create(SimulationDTO simulationDTO){
        try {
            simulationValidator.validateSimulationDTO(simulationDTO);
            if (simulationDTO.getStatus() == null) {
                simulationDTO.setStatus(EnumSimulation.In_Progress);
            }

            Simulation simulation = simulationMapper.toEntity(simulationDTO);
            simulation.setId_simulation(null);
            if (simulationRepository.existsByName(simulation.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "La simulación con ese nombre ya existe: " + simulation.getName());
            }

            try {
                simulationRepository.save(simulation);
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al crear la Simulación: posible duplicado de datos.", ex);
            }

            return "Simulación creada exitosamente";
        } catch (IllegalArgumentException e) {
            return "Error de validación: " + e.getMessage();
        } catch (Exception e) {
            return "Error al crear la Simulación: " + e.getMessage();
        }
    }

    @Override
    public String CreateCountry(SimulationDTO simulationDTO) {
        return Create(simulationDTO);
    }

    @Override
    public List<Simulation> GetAll() {
        try {
                return simulationRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los simuladores: " + e.getMessage());
        }
    }

    @Override
    public Simulation GetById(Integer simulationId) {
        try {
            simulationValidator.validateSimulationId(simulationId);

            Optional<Simulation> simulation = simulationRepository.findById(simulationId);
            if (simulation.isEmpty()) {
                throw new RuntimeException("Simulación no encontrado con ID: " + simulationId);
            }
            return simulation.get();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la simulación: " + e.getMessage());
        }
    }

    @Override
    public Simulation Update(Integer simulationId, SimulationDTO simulationDTO) {
        try {
            simulationValidator.validateSimulationId(simulationId);
            simulationValidator.validateSimulationDTO(simulationDTO);

            Optional<Simulation> simulationExistente = simulationRepository.findById(simulationId);
            if (simulationExistente.isEmpty()) {
                throw new RuntimeException("Simulación no encontrado con ID: " + simulationId);
            }

             Simulation simulation = simulationExistente.get();
             if (!simulation.getName().equals(simulationDTO.getName()) &&
                 simulationRepository.existsByName(simulationDTO.getName())) {
                 throw new ResponseStatusException(HttpStatus.CONFLICT, "La simulación con ese nombre ya existe: " + simulationDTO.getName());
             }

             simulation.setName(simulationDTO.getName());
             // Note: id_simulation is the primary key and should not be modified
             if (simulationDTO.getStatus() != null) {
                 simulation.setStatus(simulationDTO.getStatus());
             }

            return simulationRepository.save(simulation);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la simulación: " + e.getMessage());
        }
    }

    @Override
    public Simulation PartialUpdate(Integer simulationId, SimulationDTO simulationDTO) {
        try {
            simulationValidator.validateSimulationId(simulationId);

            Optional<Simulation> simulationExistente = simulationRepository.findById(simulationId);
            if (simulationExistente.isEmpty()) {
                throw new RuntimeException("Simulación no encontrado con ID: " + simulationId);
            }

             Simulation simulation = simulationExistente.get();
             if (simulationDTO.getName() != null) {
                 if (!simulation.getName().equals(simulationDTO.getName()) &&
                     simulationRepository.existsByName(simulationDTO.getName())) {
                     throw new ResponseStatusException(HttpStatus.CONFLICT, "La simulación con ese nombre ya existe: " + simulationDTO.getName());
                 }
                 simulation.setName(simulationDTO.getName());
             }
             // Note: id_simulation is the primary key and should not be modified
             if (simulationDTO.getStatus() != null) {
                 simulation.setStatus(simulationDTO.getStatus());
             }

            return simulationRepository.save(simulation);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar parcialmente la simulación: " + e.getMessage());
        }
    }

    @Override
        public boolean Delete(Integer simulationId) {
        try {
            simulationValidator.validateSimulationId(simulationId);

            Optional<Simulation> simulation = simulationRepository.findById(simulationId);
            if (simulation.isEmpty()) {
                throw new RuntimeException("Simulación no encontrado con ID: " + simulationId);
            }

            simulationRepository.deleteById(simulationId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la simulación: " + e.getMessage());
        }
    }

    @Override
    public boolean LogicalDelete(Integer simulationId) {
        try {
            simulationValidator.validateSimulationId(simulationId);

            Optional<Simulation> simulationExistente = simulationRepository.findById(simulationId);
            if (simulationExistente.isEmpty()) {
                throw new RuntimeException("Simulación no encontrado con ID: " + simulationId);
            }

            Simulation simulation = simulationExistente.get();
            simulation.setStatus(EnumSimulation.Finished);
            simulationRepository.save(simulation);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar lógicamente la simulación: " + e.getMessage());
        }
    }
}