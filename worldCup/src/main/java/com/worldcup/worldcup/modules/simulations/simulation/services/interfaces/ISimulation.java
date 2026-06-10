package com.worldcup.worldCup.modules.simulations.simulation.services.interfaces;

import com.worldcup.worldCup.modules.simulations.simulation.dto.SimulationDTO;
import com.worldcup.worldCup.modules.simulations.simulation.entity.Simulation;

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
