package com.worldcup.modules.simulations.service.interfaces;

import com.worldcup.modules.simulations.dto.TeamSimulationDTO;
import com.worldcup.modules.simulations.entity.TeamSimulation;

import java.util.List;

public interface ITeamSimulation {
    public String CreateCountry(TeamSimulationDTO teamSimulationDTO);
    public List<TeamSimulation> GetAll();
    public TeamSimulation GetById(Integer teamSimulationId);
    public TeamSimulation Update(Integer teamSimulationId, TeamSimulationDTO teamSimulationDTO);
    public TeamSimulation PartialUpdate(Integer teamSimulationId, TeamSimulationDTO teamSimulationDTO);
    public boolean Delete(Integer teamSimulationId);
    public boolean LogicalDelete(Integer teamSimulationId);

    String Create(TeamSimulationDTO teamSimulationDTO);
}
