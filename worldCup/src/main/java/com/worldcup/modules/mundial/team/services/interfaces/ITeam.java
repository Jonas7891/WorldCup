package com.worldcup.modules.mundial.team.services.interfaces;

import com.worldcup.modules.mundial.team.dto.TeamDTO;
import com.worldcup.modules.mundial.team.entity.Team;

import java.util.List;

public interface ITeam {
    public String CreateCountry(TeamDTO teamDTO);
    public List<Team> GetAll();
    public Team GetById(Integer teamId);
    public Team Update(Integer teamId, TeamDTO teamDTO);
    public Team PartialUpdate(Integer teamId, TeamDTO teamDTO);
    public boolean Delete(Integer teamId);
    public boolean LogicalDelete(Integer teamId);

    String Create(TeamDTO teamDTO);
}
