package com.worldcup.modules.simulations.service.interfaces;

import com.worldcup.modules.simulations.dto.SimulationDTO;
import com.worldcup.modules.simulations.entity.Simulation;

import java.util.List;

public interface ISimulation {
    public String CreateCountry(SimulationDTO simulationDTO);
    public List<Simulation> GetAll();
    public Simulation GetById(Integer simulationId);
    public Simulation Update(Integer simulationId, SimulationDTO simulationDTO);
    public Simulation PartialUpdate(Integer simulationId, SimulationDTO simulationDTO);
    public boolean Delete(Integer simulationId);
    public boolean LogicalDelete(Integer simulationId);

    String Create(SimulationDTO simulationDTO);
}
