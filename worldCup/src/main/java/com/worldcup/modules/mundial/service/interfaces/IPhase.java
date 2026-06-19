package com.worldcup.modules.mundial.service.interfaces;

import com.worldcup.modules.mundial.dto.PhaseDTO;
import com.worldcup.modules.mundial.entity.Phase;

import java.util.List;

public interface IPhase {
    public String CreateCountry(PhaseDTO phaseDTO);
    public List<Phase> GetAll();
    public Phase GetById(Integer phaseId);
    public Phase Update(Integer phaseId, PhaseDTO phaseDTO);
    public Phase PartialUpdate(Integer phaseId, PhaseDTO phaseDTO);
    public boolean Delete(Integer phaseId);
    public boolean LogicalDelete(Integer phaseId);

    String Create(PhaseDTO phaseDTO);
}
