package com.worldcup.worldcup.modules.simulations.match.mapper;

import com.worldcup.worldcup.modules.simulations.match.dto.MatchDTO;
import com.worldcup.worldcup.modules.simulations.match.entity.Match;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {
    public MatchDTO toDTO(Match match) {
        if (match == null) {
            return null;
        }

        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId_match(match.getId_match());
        matchDTO.setId_simulation(match.getId_simulation());
        matchDTO.setId_phase(match.getId_phase());
        matchDTO.setId_stadium(match.getId_stadium());
        matchDTO.setStatus(match.getStatus());

        return matchDTO;
    }

    public Match toEntity(MatchDTO matchDTO) {
        if (matchDTO == null) {
            return null;
        }

        Match match = new Match();
        if (matchDTO.getId_match()   != null) {
            match.setId_match(matchDTO.getId_match());
        } else {
            match.setId_match(Integer.MIN_VALUE);
        }
        match.setId_simulation(matchDTO.getId_simulation());
        match.setId_phase(matchDTO.getId_phase());
        match.setId_stadium(matchDTO.getId_stadium());
        match.setStatus(matchDTO.getStatus());

        return match;
    }
}
