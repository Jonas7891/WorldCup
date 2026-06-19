package com.worldcup.modules.mundial.validator;

import com.worldcup.modules.mundial.dto.PhaseDTO;
import org.springframework.stereotype.Component;

@Component
public class PhaseValidator {

    public boolean validatePhaseDTO(PhaseDTO phaseDTO) {
        if (phaseDTO == null) {
            return false;
        }
        if (phaseDTO.getName() == null || phaseDTO.getName().isEmpty() || phaseDTO.getName().length() > 50) {
            throw new IllegalArgumentException("El nombre de la fase no puede estar vacío y/o superar los 50 caracteres.");
        }
        if (phaseDTO.getPhase_order() == null || phaseDTO.getPhase_order() <= 0) {
            throw new IllegalArgumentException("El orden de la fase no puede ser menor que 0.");
        }
        return true;
    }

    public void validatePhaseId(Integer phaseId) {
        if (phaseId == null || phaseId <= 0) {
            throw new IllegalArgumentException("El identificador de la fase debe ser mayor que cero.");
        }
    }
}
