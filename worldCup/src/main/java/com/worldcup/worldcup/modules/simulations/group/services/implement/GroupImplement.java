package com.worldcup.worldcup.modules.mundial.team.services.implement;

import com.worldcup.worldcup.modules.mundial.team.dto.TeamDTO;
import com.worldcup.worldcup.modules.mundial.team.entity.Team;
import com.worldcup.worldcup.modules.mundial.team.mapper.TeamMapper;
import com.worldcup.worldcup.modules.mundial.team.repository.TeamRepository;
import com.worldcup.worldcup.modules.mundial.team.services.interfaces.ITeam;
import com.worldcup.worldcup.modules.mundial.team.validator.TeamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamImplement implements ITeam {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamValidator teamValidator;

    @Override
    public String Create(TeamDTO teamDTO){
        try {
            teamValidator.validateTeamDTO(teamDTO);
            if (teamDTO.getStatus() == null) {
                teamDTO.setStatus(Boolean.TRUE);
            }

            Team team = teamMapper.toEntity(teamDTO);
            // Ensure id is null so JPA will insert a new row
            team.setId_team(null);
            // Check for duplicates by name before insert
            if (teamRepository.existsByName(team.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El equipo con ese nombre ya existe: " + team.getName());
            }

            try {
                teamRepository.save(team);
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al crear el equipo: posible duplicado de datos.", ex);
            }

            return "Equipo creado exitosamente";
        } catch (IllegalArgumentException e) {
            return "Error de validación: " + e.getMessage();
        } catch (Exception e) {
            return "Error al crear el Equipo: " + e.getMessage();
        }
    }

    @Override
    public String CreateCountry(TeamDTO teamDTO) {
        return Create(teamDTO);
    }

    @Override
    public List<Team> GetAll() {
        try {
                return teamRepository.findByStatusTrue();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los equipos: " + e.getMessage());
        }
    }

    @Override
    public Team GetById(Integer teamId) {
        try {
            teamValidator.validateTeamId(teamId);

            Optional<Team> team = teamRepository.findById(teamId);
            if (team.isEmpty()) {
                throw new RuntimeException("Equipo no encontrado con ID: " + teamId);
            }
            return team.get();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el equipo: " + e.getMessage());
        }
    }

    @Override
    public Team Update(Integer teamId, TeamDTO teamDTO) {
        try {
            teamValidator.validateTeamId(teamId);
            teamValidator.validateTeamDTO(teamDTO);

            Optional<Team> teamExistente = teamRepository.findById(teamId);
            if (teamExistente.isEmpty()) {
                throw new RuntimeException("Equipo no encontrado con ID: " + teamId);
            }

            Team team = teamExistente.get();
            // Check if new name already exists and is different from current
            if (!team.getName().equals(teamDTO.getName()) &&
                teamRepository.existsByName(teamDTO.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El equipo con ese nombre ya existe: " + teamDTO.getName());
            }

            team.setName(teamDTO.getName());
            team.setId_group(teamDTO.getId_group());
            team.setId_country(teamDTO.getId_country());
            if (teamDTO.getStatus() != null) {
                team.setStatus(teamDTO.getStatus());
            }

            return teamRepository.save(team);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el equipo: " + e.getMessage());
        }
    }

    @Override
    public Team PartialUpdate(Integer teamId, TeamDTO teamDTO) {
        try {
            teamValidator.validateTeamId(teamId);

            Optional<Team> teamExistente = teamRepository.findById(teamId);
            if (teamExistente.isEmpty()) {
                throw new RuntimeException("Equipo no encontrado con ID: " + teamId);
            }

            Team team = teamExistente.get();
            if (teamDTO.getName() != null) {
                // Check if new name already exists
                if (!team.getName().equals(teamDTO.getName()) &&
                    teamRepository.existsByName(teamDTO.getName())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "El equipo con ese nombre ya existe: " + teamDTO.getName());
                }
                team.setName(teamDTO.getName());
            }
            if (teamDTO.getId_group() > 0) {
                team.setId_group(teamDTO.getId_group());
            }
            if (teamDTO.getId_country() > 0) {
                team.setId_country(teamDTO.getId_country());
            }
            if (teamDTO.getStatus() != null) {
                team.setStatus(teamDTO.getStatus());
            }

            return teamRepository.save(team);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar parcialmente el equipo: " + e.getMessage());
        }
    }

    @Override
        public boolean Delete(Integer teamId) {
        try {
            teamValidator.validateTeamId(teamId);

            Optional<Team> team = teamRepository.findById(teamId);
            if (team.isEmpty()) {
                throw new RuntimeException("Equipo no encontrado con ID: " + teamId);
            }

            teamRepository.deleteById(teamId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el equipo: " + e.getMessage());
        }
    }

    @Override
    public boolean LogicalDelete(Integer teamId) {
        try {
            teamValidator.validateTeamId(teamId);

            Optional<Team> teamExistente = teamRepository.findById(teamId);
            if (teamExistente.isEmpty()) {
                throw new RuntimeException("Equipo no encontrado con ID: " + teamId);
            }

            Team team = teamExistente.get();
            team.setStatus(Boolean.FALSE);
            teamRepository.save(team);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar lógicamente el equipo: " + e.getMessage());
        }
    }
}