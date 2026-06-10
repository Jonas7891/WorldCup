package com.worldcup.worldCup.modules.mundial.phase.mapper;

import com.worldcup.worldCup.modules.mundial.phase.dto.PhaseDTO;
import com.worldcup.worldCup.modules.mundial.phase.entity.Phase;
import org.springframework.stereotype.Component;

@Component
public class PhaseMapper {
    public PhaseDTO toDTO(Phase phase) {
        if (phase == null) {
            return null;
        }

        PhaseDTO phaseDTO = new PhaseDTO();
        phaseDTO.setId_phase(phase.getId_phase());
        phaseDTO.setName(phase.getName());
        phaseDTO.setPhase_order(phase.getPhase_order());
        phaseDTO.setStatus(phase.getStatus());

        return phaseDTO;
    }

    public Phase toEntity(PhaseDTO phaseDTO) {
        if (phaseDTO == null) {
            return null;
        }

        Phase phase = new Phase();
        if (phaseDTO.getId_phase() != null) {
            phase.setId_phase(phaseDTO.getId_phase());
        } else {
            phase.setId_phase(null);
        }
        phase.setName(phaseDTO.getName());
        phase.setPhase_order(phaseDTO.getPhase_order());
        phase.setStatus(phaseDTO.getStatus());

        return phase;
    }
}
