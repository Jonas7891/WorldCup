package com.worldcup.modules.mundial.team.validator;

import com.worldcup.modules.mundial.team.dto.TeamDTO;
import org.springframework.stereotype.Component;

@Component
public class TeamValidator {

    public boolean validateTeamDTO(TeamDTO teamDTO) {
        if (teamDTO == null) {
            return false;
        }
        if (teamDTO.getName() == null || teamDTO.getName().isEmpty() || teamDTO.getName().length() > 100) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío y/o superar los 100 caracteres.");
        }
        if (teamDTO.getId_group() <= 0) {
            throw new IllegalArgumentException("El identificador del grupo debe ser mayor que cero.");
        }
        if (teamDTO.getId_country() <= 0) {
            throw new IllegalArgumentException("El identificador del país debe ser mayor que cero.");
        }
        return true;
    }

    public void validateTeamId(Integer teamId) {
        if (teamId == null || teamId <= 0) {
            throw new IllegalArgumentException("El identificador del equipo debe ser mayor que cero.");
        }
    }
}
