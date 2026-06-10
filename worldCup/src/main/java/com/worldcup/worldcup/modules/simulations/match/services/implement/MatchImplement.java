package com.worldcup.worldCup.modules.simulations.match.services.implement;

import com.worldcup.worldCup.modules.mundial.phase.entity.Phase;
import com.worldcup.worldCup.modules.mundial.phase.repository.PhaseRepository;
import com.worldcup.worldCup.modules.mundial.stadium.entity.Stadium;
import com.worldcup.worldCup.modules.mundial.stadium.repository.StadiumRepository;
import com.worldcup.worldCup.modules.simulations.match.dto.MatchDTO;
import com.worldcup.worldCup.modules.simulations.match.entity.EnumMatch;
import com.worldcup.worldCup.modules.simulations.match.entity.Match;
import com.worldcup.worldCup.modules.simulations.match.mapper.MatchMapper;
import com.worldcup.worldCup.modules.simulations.match.repository.MatchRepository;
import com.worldcup.worldCup.modules.simulations.match.services.interfaces.IMatch;
import com.worldcup.worldCup.modules.simulations.match.validator.MatchValidator;
import com.worldcup.worldCup.modules.simulations.simulation.entity.Simulation;
import com.worldcup.worldCup.modules.simulations.simulation.repository.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MatchImplement implements IMatch {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    private MatchValidator matchValidator;

    @Autowired
    private SimulationRepository simulationRepository;

    @Autowired
    private PhaseRepository phaseRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Override
    public String Create(MatchDTO matchDTO){
        try {
            matchValidator.validateMatchDTO(matchDTO);
            if (matchDTO.getStatus() == null) {
                matchDTO.setStatus(EnumMatch.PENDING);
            }

            Match match = matchMapper.toEntity(matchDTO);

            try {
                matchRepository.save(match);
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al crear el Partido: posible duplicado de datos.", ex);
            }

            return "Partido creado exitosamente";
        } catch (IllegalArgumentException e) {
            return "Error de validación: " + e.getMessage();
        } catch (Exception e) {
            return "Error al crear el Partido: " + e.getMessage();
        }
    }

    @Override
    public String CreateCountry(MatchDTO matchDTO) {
        return Create(matchDTO);
    }

    @Override
    public List<Match> GetAll() {
        try {
                return matchRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los partidos: " + e.getMessage());
        }
    }

    @Override
    public Match GetById(Integer matchId) {
        try {
            matchValidator.validateMatchId(matchId);

            Optional<Match> group = matchRepository.findById(matchId);
            if (group.isEmpty()) {
                throw new RuntimeException("Partido no encontrado con ID: " + matchId);
            }
            return group.get();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el partido: " + e.getMessage());
        }
    }

    @Override
    public Match Update(Integer matchId, MatchDTO matchDTO) {
        try {
            matchValidator.validateMatchId(matchId);
            matchValidator.validateMatchDTO(matchDTO);

            Optional<Match> matchExistente = matchRepository.findById(matchId);
            if (matchExistente.isEmpty()) {
                throw new RuntimeException("Partido no encontrado con ID: " + matchId);
            }

            Match match = matchExistente.get();

            if (matchDTO.getId_simulation() != null && matchDTO.getId_simulation() > 0) {
                Simulation simulation = simulationRepository.findById(matchDTO.getId_simulation()).orElse(null);
                match.setSimulation(simulation);
            }
            if (matchDTO.getId_phase() != null && matchDTO.getId_phase() > 0) {
                Phase phase = phaseRepository.findById(matchDTO.getId_phase()).orElse(null);
                match.setPhase(phase);
            }
            if (matchDTO.getId_stadium() != null && matchDTO.getId_stadium() > 0) {
                Stadium stadium = stadiumRepository.findById(matchDTO.getId_stadium()).orElse(null);
                match.setStadium(stadium);
            }
            if (matchDTO.getMatchDate() != null) {
                match.setMatch_date(matchDTO.getMatchDate());
            }
            if (matchDTO.getStatus() != null) {
                match.setStatus(matchDTO.getStatus());
            }

            return matchRepository.save(match);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el partido: " + e.getMessage());
        }
    }

    @Override
    public Match PartialUpdate(Integer matchId, MatchDTO matchDTO) {
        try {
            matchValidator.validateMatchId(matchId);

            Optional<Match> matchExistente = matchRepository.findById(matchId);
            if (matchExistente.isEmpty()) {
                throw new RuntimeException("Partido no encontrado con ID: " + matchId);
            }

            Match match = matchExistente.get();

            if (matchDTO.getId_simulation() != null && matchDTO.getId_simulation() > 0) {
                Simulation simulation = simulationRepository.findById(matchDTO.getId_simulation()).orElse(null);
                match.setSimulation(simulation);
            }
            if (matchDTO.getId_phase() != null && matchDTO.getId_phase() > 0) {
                Phase phase = phaseRepository.findById(matchDTO.getId_phase()).orElse(null);
                match.setPhase(phase);
            }
            if (matchDTO.getId_stadium() != null && matchDTO.getId_stadium() > 0) {
                Stadium stadium = stadiumRepository.findById(matchDTO.getId_stadium()).orElse(null);
                match.setStadium(stadium);
            }
            if (matchDTO.getMatchDate() != null) {
                match.setMatch_date(matchDTO.getMatchDate());
            }
            if (matchDTO.getStatus() != null) {
                match.setStatus(matchDTO.getStatus());
            }

            return matchRepository.save(match);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar parcialmente el partido: " + e.getMessage());
        }
    }

    @Override
        public boolean Delete(Integer matchId) {
        try {
            matchValidator.validateMatchId(matchId);

            Optional<Match> group = matchRepository.findById(matchId);
            if (group.isEmpty()) {
                throw new RuntimeException("Partido no encontrado con ID: " + matchId);
            }

            matchRepository.deleteById(matchId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el partido: " + e.getMessage());
        }
    }

    @Override
    public boolean LogicalDelete(Integer matchId) {
        try {
            matchValidator.validateMatchId(matchId);

            Optional<Match> matchExistente = matchRepository.findById(matchId);
            if (matchExistente.isEmpty()) {
                throw new RuntimeException("Partido no encontrado con ID: " + matchId);
            }

            Match match = matchExistente.get();
            match.setStatus(EnumMatch.PENDING);
            matchRepository.save(match);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar lógicamente el partido: " + e.getMessage());
        }
    }
}