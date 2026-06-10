package com.worldcup.worldcup.modules.simulations.match.validator;

import com.worldcup.worldcup.modules.simulations.match.dto.MatchDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class MatchValidator {

    public boolean validateMatchDTO(MatchDTO matchDTO) {
        if (matchDTO == null) {
            return false;
        }
        if (matchDTO.getId_simulation() == null || matchDTO.getId_simulation() <= 0) {
             throw new IllegalArgumentException("El identificador de la simulación debe ser mayor que cero.");
        }
        if (matchDTO.getId_phase() == null || matchDTO.getId_phase() <= 0) {
            throw new IllegalArgumentException("El identificador de la fase debe ser mayor que cero.");
        }
        if (matchDTO.getId_stadium() == null || matchDTO.getId_stadium() <= 0) {
            throw new IllegalArgumentException("El identificador del estadio debe ser mayor que cero.");
        }
        if (matchDTO.getMatchDate() == null) {
            throw new IllegalArgumentException("La fecha del partido no puede ser nula.");
        }
        if (matchDTO.getStatus() == null) {
            throw new IllegalArgumentException("El estado del partido es obligatorio.");
        }
        return true;
    }

    public void validateMatchId(Integer matchId) {
        if (matchId == null || matchId <= 0) {
            throw new IllegalArgumentException("El identificador del grupo debe ser mayor que cero.");
        }
    }
}
