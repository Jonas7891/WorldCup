package com.worldcup.worldcup.modules.simulations.teamSimulation.services.interfaces;

import com.worldcup.worldcup.modules.simulations.teamSimulation.dto.TeamSimulationDTO;
import com.worldcup.worldcup.modules.simulations.teamSimulation.entity.TeamSimulation;

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
