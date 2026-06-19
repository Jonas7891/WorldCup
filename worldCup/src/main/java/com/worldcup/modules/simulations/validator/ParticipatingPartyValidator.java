package com.worldcup.modules.simulations.validator;

import com.worldcup.modules.simulations.dto.ParticipatingPartyDTO;
import org.springframework.stereotype.Component;

@Component
public class ParticipatingPartyValidator {

    public boolean validateParticipatingPartyDTO(ParticipatingPartyDTO dto) {
        if (dto == null) {
            return false;
        }

        if (dto.getId_match() == null || dto.getId_match() <= 0) {
            throw new IllegalArgumentException("El identificador del partido debe ser mayor que cero.");
        }

        if (dto.getId_team() == null || dto.getId_team() <= 0) {
            throw new IllegalArgumentException("El identificador del equipo debe ser mayor que cero.");
        }

        if (dto.getGoals() == null || dto.getGoals() < 0) {
            throw new IllegalArgumentException("Los goles no pueden ser negativos.");
        }

        return true;
    }

    public void validateParticipatingPartyId(Integer participatingPartyId) {
        if (participatingPartyId == null || participatingPartyId <= 0) {
            throw new IllegalArgumentException("El identificador del participante del partido debe ser mayor que cero.");
        }
    }
}

