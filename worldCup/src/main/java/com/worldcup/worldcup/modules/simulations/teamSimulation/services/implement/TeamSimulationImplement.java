package com.worldcup.worldCup.modules.simulations.teamSimulation.services.implement;

import com.worldcup.worldCup.modules.mundial.team.entity.Team;
import com.worldcup.worldCup.modules.mundial.team.repository.TeamRepository;
import com.worldcup.worldCup.modules.simulations.simulation.entity.Simulation;
import com.worldcup.worldCup.modules.simulations.simulation.repository.SimulationRepository;
import com.worldcup.worldCup.modules.simulations.teamSimulation.dto.TeamSimulationDTO;
import com.worldcup.worldCup.modules.simulations.teamSimulation.entity.TeamSimulation;
import com.worldcup.worldCup.modules.simulations.teamSimulation.mapper.TeamSimulationMapper;
import com.worldcup.worldCup.modules.simulations.teamSimulation.repository.TeamSimulationRepository;
import com.worldcup.worldCup.modules.simulations.teamSimulation.services.interfaces.ITeamSimulation;
import com.worldcup.worldCup.modules.simulations.teamSimulation.validator.TeamSimulationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TeamSimulationImplement implements ITeamSimulation {
    @Autowired
    private TeamSimulationRepository teamSimulationRepository;

    @Autowired
    private TeamSimulationMapper teamSimulationMapper;

    @Autowired
    private TeamSimulationValidator teamSimulationValidator;

    @Autowired
    private SimulationRepository simulationRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public String Create(TeamSimulationDTO teamSimulationDTO) {
        try {
            // Validar el DTO
            teamSimulationValidator.validateTeamSimulationDTO(teamSimulationDTO);

            // Verificar que existan la simulación y el equipo
            Optional<Simulation> simulation = simulationRepository.findById(teamSimulationDTO.getId_simulation());
            if (simulation.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Simulación no encontrada con ID: " + teamSimulationDTO.getId_simulation());
            }

            Optional<Team> team = teamRepository.findById(teamSimulationDTO.getId_team());
            if (team.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado con ID: " + teamSimulationDTO.getId_team());
            }

            // Verificar que no exista ya una asociación similar
            List<TeamSimulation> existing = teamSimulationRepository.findBySimulationIdAndTeamId(
                    teamSimulationDTO.getId_simulation(),
                    teamSimulationDTO.getId_team()
            );
            if (!existing.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe una asociación entre esta Simulación y este Equipo");
            }

            // Crear la entidad TeamSimulation
            TeamSimulation teamSimulation = teamSimulationMapper.toEntity(teamSimulationDTO);
            teamSimulation.setSimulation(simulation.get());
            teamSimulation.setTeam(team.get());
            teamSimulation.setStatus(teamSimulationDTO.getStatus() != null ? teamSimulationDTO.getStatus() : true);

            try {
                teamSimulationRepository.save(teamSimulation);
                return "Team Simulation creada exitosamente";
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Error al crear la Team Simulation: posible duplicado de datos.", ex);
            }

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validación: " + e.getMessage());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al crear la Team Simulation: " + e.getMessage());
        }
    }

    @Override
    public String CreateCountry(TeamSimulationDTO teamSimulationDTO) {
        return Create(teamSimulationDTO);
    }

    @Override
    public List<TeamSimulation> GetAll() {
        try {
            return teamSimulationRepository.findByStatusTrue();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al obtener los Team Simulations: " + e.getMessage());
        }
    }

    @Override
    public TeamSimulation GetById(Integer teamSimulationId) {
        try {
            teamSimulationValidator.validateTeamSimulationId(teamSimulationId);

            Optional<TeamSimulation> teamSimulation = teamSimulationRepository.findById(teamSimulationId);
            if (teamSimulation.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Team Simulation no encontrada con ID: " + teamSimulationId);
            }
            return teamSimulation.get();
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al obtener la Team Simulation: " + e.getMessage());
        }
    }

    @Override
    public TeamSimulation Update(Integer teamSimulationId, TeamSimulationDTO teamSimulationDTO) {
        try {
            teamSimulationValidator.validateTeamSimulationId(teamSimulationId);
            teamSimulationValidator.validateTeamSimulationDTO(teamSimulationDTO);

            Optional<TeamSimulation> teamSimulationExistente = teamSimulationRepository.findById(teamSimulationId);
            if (teamSimulationExistente.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Team Simulation no encontrada con ID: " + teamSimulationId);
            }

            // Verificar que la nueva simulación y equipo existan
            Optional<Simulation> simulation = simulationRepository.findById(teamSimulationDTO.getId_simulation());
            if (simulation.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Simulación no encontrada con ID: " + teamSimulationDTO.getId_simulation());
            }

            Optional<Team> team = teamRepository.findById(teamSimulationDTO.getId_team());
            if (team.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Equipo no encontrado con ID: " + teamSimulationDTO.getId_team());
            }

            TeamSimulation teamSimulation = teamSimulationExistente.get();
            teamSimulation.setSimulation(simulation.get());
            teamSimulation.setTeam(team.get());
            if (teamSimulationDTO.getStatus() != null) {
                teamSimulation.setStatus(teamSimulationDTO.getStatus());
            }

            return teamSimulationRepository.save(teamSimulation);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al actualizar la Team Simulation: " + e.getMessage());
        }
    }

    @Override
    public TeamSimulation PartialUpdate(Integer teamSimulationId, TeamSimulationDTO teamSimulationDTO) {
        try {
            teamSimulationValidator.validateTeamSimulationId(teamSimulationId);

            Optional<TeamSimulation> teamSimulationExistente = teamSimulationRepository.findById(teamSimulationId);
            if (teamSimulationExistente.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Team Simulation no encontrada con ID: " + teamSimulationId);
            }

            TeamSimulation teamSimulation = teamSimulationExistente.get();

            // Actualizar solo los campos que no sean nulos
            if (teamSimulationDTO.getId_simulation() != null && teamSimulationDTO.getId_simulation() > 0) {
                Optional<Simulation> simulation = simulationRepository.findById(teamSimulationDTO.getId_simulation());
                if (simulation.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Simulación no encontrada con ID: " + teamSimulationDTO.getId_simulation());
                }
                teamSimulation.setSimulation(simulation.get());
            }

            if (teamSimulationDTO.getId_team() != null && teamSimulationDTO.getId_team() > 0) {
                Optional<Team> team = teamRepository.findById(teamSimulationDTO.getId_team());
                if (team.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Equipo no encontrado con ID: " + teamSimulationDTO.getId_team());
                }
                teamSimulation.setTeam(team.get());
            }

            if (teamSimulationDTO.getStatus() != null) {
                teamSimulation.setStatus(teamSimulationDTO.getStatus());
            }

            return teamSimulationRepository.save(teamSimulation);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al actualizar parcialmente la Team Simulation: " + e.getMessage());
        }
    }

    @Override
    public boolean Delete(Integer teamSimulationId) {
        try {
            teamSimulationValidator.validateTeamSimulationId(teamSimulationId);

            Optional<TeamSimulation> teamSimulation = teamSimulationRepository.findById(teamSimulationId);
            if (teamSimulation.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Team Simulation no encontrada con ID: " + teamSimulationId);
            }

            teamSimulationRepository.deleteById(teamSimulationId);
            return true;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al eliminar la Team Simulation: " + e.getMessage());
        }
    }

    @Override
    public boolean LogicalDelete(Integer teamSimulationId) {
        try {
            teamSimulationValidator.validateTeamSimulationId(teamSimulationId);

            Optional<TeamSimulation> teamSimulationExistente = teamSimulationRepository.findById(teamSimulationId);
            if (teamSimulationExistente.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Team Simulation no encontrada con ID: " + teamSimulationId);
            }

            TeamSimulation teamSimulation = teamSimulationExistente.get();
            teamSimulation.setStatus(false);
            teamSimulationRepository.save(teamSimulation);
            return true;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al eliminar lógicamente la Team Simulation: " + e.getMessage());
        }
    }
}