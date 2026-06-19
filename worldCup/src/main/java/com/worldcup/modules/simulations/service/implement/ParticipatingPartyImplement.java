package com.worldcup.modules.simulations.service.implement;

import com.worldcup.modules.mundial.entity.Team;
import com.worldcup.modules.mundial.repository.TeamRepository;
import com.worldcup.modules.simulations.entity.Match;
import com.worldcup.modules.simulations.repository.MatchRepository;
import com.worldcup.modules.simulations.dto.ParticipatingPartyDTO;
import com.worldcup.modules.simulations.entity.ParticipatingParty;
import com.worldcup.modules.simulations.mapper.ParticipatingPartyMapper;
import com.worldcup.modules.simulations.repository.ParticipatingPartyRepository;
import com.worldcup.modules.simulations.service.interfaces.IParticipatingParty;
import com.worldcup.modules.simulations.validator.ParticipatingPartyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipatingPartyImplement implements IParticipatingParty {

    @Autowired
    private ParticipatingPartyRepository participatingPartyRepository;

    @Autowired
    private ParticipatingPartyMapper mapper;

    @Autowired
    private ParticipatingPartyValidator validator;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public String Create(ParticipatingPartyDTO dto) {
        try {
            // Validar el DTO
            validator.validateParticipatingPartyDTO(dto);

            // Verificar que existan el partido y el equipo
            Optional<Match> match = matchRepository.findById(dto.getId_match());
            if (match.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Partido no encontrado con ID: " + dto.getId_match());
            }

            Optional<Team> team = teamRepository.findById(dto.getId_team());
            if (team.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Equipo no encontrado con ID: " + dto.getId_team());
            }

            // Verificar que no exista ya una asociación similar
            List<ParticipatingParty> existing = participatingPartyRepository.findByMatchIdAndTeamId(
                    dto.getId_match(),
                    dto.getId_team()
            );
            if (!existing.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un participante para este partido y equipo");
            }

            // Crear la entidad
            ParticipatingParty participatingParty = mapper.toEntity(dto);
            participatingParty.setMatch(match.get());
            participatingParty.setTeam(team.get());

            try {
                participatingPartyRepository.save(participatingParty);
                return "Participante del partido creado exitosamente";
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Error al crear el participante: posible duplicado de datos.", ex);
            }

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Error de validación: " + e.getMessage());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al crear el participante del partido: " + e.getMessage());
        }
    }

    @Override
    public List<ParticipatingParty> GetAll() {
        try {
            return participatingPartyRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al obtener los participantes: " + e.getMessage());
        }
    }

    @Override
    public ParticipatingParty GetById(Integer participatingPartyId) {
        try {
            validator.validateParticipatingPartyId(participatingPartyId);

            Optional<ParticipatingParty> participatingParty = participatingPartyRepository.findById(participatingPartyId);
            if (participatingParty.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Participante no encontrado con ID: " + participatingPartyId);
            }
            return participatingParty.get();
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al obtener el participante: " + e.getMessage());
        }
    }

    @Override
    public ParticipatingParty Update(Integer participatingPartyId, ParticipatingPartyDTO dto) {
        try {
            validator.validateParticipatingPartyId(participatingPartyId);
            validator.validateParticipatingPartyDTO(dto);

            Optional<ParticipatingParty> existing = participatingPartyRepository.findById(participatingPartyId);
            if (existing.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Participante no encontrado con ID: " + participatingPartyId);
            }

            // Verificar que el partido y equipo existan
            Optional<Match> match = matchRepository.findById(dto.getId_match());
            if (match.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Partido no encontrado con ID: " + dto.getId_match());
            }

            Optional<Team> team = teamRepository.findById(dto.getId_team());
            if (team.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Equipo no encontrado con ID: " + dto.getId_team());
            }

            ParticipatingParty participatingParty = existing.get();
            participatingParty.setMatch(match.get());
            participatingParty.setTeam(team.get());
            participatingParty.setGoals(dto.getGoals() != null ? dto.getGoals() : 0);
            participatingParty.setResult(dto.getResult());

            return participatingPartyRepository.save(participatingParty);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al actualizar el participante: " + e.getMessage());
        }
    }

    @Override
    public ParticipatingParty PartialUpdate(Integer participatingPartyId, ParticipatingPartyDTO dto) {
        try {
            validator.validateParticipatingPartyId(participatingPartyId);

            Optional<ParticipatingParty> existing = participatingPartyRepository.findById(participatingPartyId);
            if (existing.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Participante no encontrado con ID: " + participatingPartyId);
            }

            ParticipatingParty participatingParty = existing.get();

            // Actualizar solo los campos que no sean nulos
            if (dto.getId_match() != null && dto.getId_match() > 0) {
                Optional<Match> match = matchRepository.findById(dto.getId_match());
                if (match.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Partido no encontrado con ID: " + dto.getId_match());
                }
                participatingParty.setMatch(match.get());
            }

            if (dto.getId_team() != null && dto.getId_team() > 0) {
                Optional<Team> team = teamRepository.findById(dto.getId_team());
                if (team.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Equipo no encontrado con ID: " + dto.getId_team());
                }
                participatingParty.setTeam(team.get());
            }

            if (dto.getGoals() != null && dto.getGoals() >= 0) {
                participatingParty.setGoals(dto.getGoals());
            }

            if (dto.getResult() != null) {
                participatingParty.setResult(dto.getResult());
            }

            return participatingPartyRepository.save(participatingParty);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al actualizar parcialmente el participante: " + e.getMessage());
        }
    }

    @Override
    public boolean Delete(Integer participatingPartyId) {
        try {
            validator.validateParticipatingPartyId(participatingPartyId);

            Optional<ParticipatingParty> participatingParty = participatingPartyRepository.findById(participatingPartyId);
            if (participatingParty.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Participante no encontrado con ID: " + participatingPartyId);
            }

            participatingPartyRepository.deleteById(participatingPartyId);
            return true;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al eliminar el participante: " + e.getMessage());
        }
    }
}

