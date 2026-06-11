package com.worldcup.modules.simulations.match.mapper;

import com.worldcup.modules.mundial.phase.entity.Phase;
import com.worldcup.modules.mundial.phase.repository.PhaseRepository;
import com.worldcup.modules.mundial.stadium.entity.Stadium;
import com.worldcup.modules.mundial.stadium.repository.StadiumRepository;
import com.worldcup.modules.simulations.match.dto.MatchDTO;
import com.worldcup.modules.simulations.match.entity.Match;
import com.worldcup.modules.simulations.simulation.entity.Simulation;
import com.worldcup.modules.simulations.simulation.repository.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {
    @Autowired
    private SimulationRepository simulationRepository;

    @Autowired
    private PhaseRepository phaseRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    public MatchDTO toDTO(Match match) {
        if (match == null) {
            return null;
        }

        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId_match(match.getId_match());
        matchDTO.setId_simulation(match.getSimulation() != null ? match.getSimulation().getId_simulation() : null);
        matchDTO.setId_phase(match.getPhase() != null ? match.getPhase().getId_phase() : null);
        matchDTO.setId_stadium(match.getStadium() != null ? match.getStadium().getId_stadium() : null);
        matchDTO.setMatchDate(match.getMatch_date());
        matchDTO.setStatus(match.getStatus());

        return matchDTO;
    }

    public Match toEntity(MatchDTO matchDTO) {
        if (matchDTO == null) {
            return null;
        }

        Match match = new Match();
        if (matchDTO.getId_match() != null) {
            match.setId_match(matchDTO.getId_match());
        } else {
            match.setId_match(Integer.MIN_VALUE);
        }

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

        match.setMatch_date(matchDTO.getMatchDate());
        match.setStatus(matchDTO.getStatus());

        return match;
    }
}


