package com.worldcup.worldcup.modules.results.participantOfficialParty.services.implement;

import com.worldcup.worldcup.modules.mundial.team.entity.Team;
import com.worldcup.worldcup.modules.mundial.team.repository.TeamRepository;
import com.worldcup.worldcup.modules.results.oficialMatches.entity.OficialMatch;
import com.worldcup.worldcup.modules.results.oficialMatches.repository.OficialMatchRepository;
import com.worldcup.worldcup.modules.results.participantOfficialParty.dto.ParticipantOfficialPartyDTO;
import com.worldcup.worldcup.modules.results.participantOfficialParty.entity.ParticipantOfficialParty;
import com.worldcup.worldcup.modules.results.participantOfficialParty.mapper.ParticipantOfficialPartyMapper;
import com.worldcup.worldcup.modules.results.participantOfficialParty.repository.ParticipantOfficialPartyRepository;
import com.worldcup.worldcup.modules.results.participantOfficialParty.services.interfaces.IParticipantOfficialParty;
import com.worldcup.worldcup.modules.results.participantOfficialParty.validator.ParticipantOfficialPartyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantOfficialPartyImplement implements IParticipantOfficialParty {

    @Autowired
    private ParticipantOfficialPartyRepository repository;

    @Autowired
    private ParticipantOfficialPartyMapper mapper;

    @Autowired
    private ParticipantOfficialPartyValidator validator;

    @Autowired
    private OficialMatchRepository oficial_match_repository;

    @Autowired
    private TeamRepository team_repository;

    @Override
    public String Create(ParticipantOfficialPartyDTO dto) {
        try {
            validator.validateParticipantOfficialPartyDTO(dto);

            Optional<OficialMatch> oficial_match = oficial_match_repository.findById(dto.getId_official_match());
            if (oficial_match.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Official match not found with ID: " + dto.getId_official_match());
            }

            Optional<Team> team = team_repository.findById(dto.getId_team());
            if (team.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Team not found with ID: " + dto.getId_team());
            }

            List<ParticipantOfficialParty> existing = repository.findByOfficialMatchIdAndTeamId(
                    dto.getId_official_match(),
                    dto.getId_team()
            );
            if (!existing.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "A participant already exists for this official match and team");
            }

            ParticipantOfficialParty entity = mapper.toEntity(dto);
            entity.setOfficial_match(oficial_match.get());
            entity.setTeam(team.get());

            try {
                repository.save(entity);
                return "Participant official party created successfully";
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Error creating participant: possible duplicate data.", ex);
            }

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Validation error: " + e.getMessage());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error creating participant official party: " + e.getMessage());
        }
    }

    @Override
    public List<ParticipantOfficialParty> GetAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error retrieving participants: " + e.getMessage());
        }
    }

    @Override
    public ParticipantOfficialParty GetById(Integer id) {
        try {
            validator.validateParticipantOfficialPartyId(id);

            Optional<ParticipantOfficialParty> entity = repository.findById(id);
            if (entity.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Participant not found with ID: " + id);
            }
            return entity.get();
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error retrieving participant: " + e.getMessage());
        }
    }

    @Override
    public ParticipantOfficialParty Update(Integer id, ParticipantOfficialPartyDTO dto) {
        try {
            validator.validateParticipantOfficialPartyId(id);
            validator.validateParticipantOfficialPartyDTO(dto);

            Optional<ParticipantOfficialParty> existing = repository.findById(id);
            if (existing.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Participant not found with ID: " + id);
            }

            Optional<OficialMatch> oficial_match = oficial_match_repository.findById(dto.getId_official_match());
            if (oficial_match.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Official match not found with ID: " + dto.getId_official_match());
            }

            Optional<Team> team = team_repository.findById(dto.getId_team());
            if (team.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Team not found with ID: " + dto.getId_team());
            }

            ParticipantOfficialParty entity = existing.get();
            entity.setOfficial_match(oficial_match.get());
            entity.setTeam(team.get());
            entity.setGoals(dto.getGoals() != null ? dto.getGoals() : 0);
            entity.setResult(dto.getResult());

            return repository.save(entity);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error updating participant: " + e.getMessage());
        }
    }

    @Override
    public ParticipantOfficialParty PartialUpdate(Integer id, ParticipantOfficialPartyDTO dto) {
        try {
            validator.validateParticipantOfficialPartyId(id);

            Optional<ParticipantOfficialParty> existing = repository.findById(id);
            if (existing.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Participant not found with ID: " + id);
            }

            ParticipantOfficialParty entity = existing.get();

            if (dto.getId_official_match() != null && dto.getId_official_match() > 0) {
                Optional<OficialMatch> oficial_match = oficial_match_repository.findById(dto.getId_official_match());
                if (oficial_match.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Official match not found with ID: " + dto.getId_official_match());
                }
                entity.setOfficial_match(oficial_match.get());
            }

            if (dto.getId_team() != null && dto.getId_team() > 0) {
                Optional<Team> team = team_repository.findById(dto.getId_team());
                if (team.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Team not found with ID: " + dto.getId_team());
                }
                entity.setTeam(team.get());
            }

            if (dto.getGoals() != null && dto.getGoals() >= 0) {
                entity.setGoals(dto.getGoals());
            }

            if (dto.getResult() != null) {
                entity.setResult(dto.getResult());
            }

            return repository.save(entity);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error partially updating participant: " + e.getMessage());
        }
    }

    @Override
    public boolean Delete(Integer id) {
        try {
            validator.validateParticipantOfficialPartyId(id);

            Optional<ParticipantOfficialParty> entity = repository.findById(id);
            if (entity.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Participant not found with ID: " + id);
            }

            repository.deleteById(id);
            return true;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error deleting participant: " + e.getMessage());
        }
    }
}

