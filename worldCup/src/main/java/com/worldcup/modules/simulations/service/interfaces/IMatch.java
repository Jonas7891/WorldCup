package com.worldcup.modules.simulations.service.interfaces;

import com.worldcup.modules.simulations.dto.MatchDTO;
import com.worldcup.modules.simulations.entity.Match;

import java.util.List;

public interface IMatch {
    public String CreateCountry(MatchDTO matchDTO);
    public List<Match> GetAll();
    public Match GetById(Integer matchId);
    public Match Update(Integer matchId, MatchDTO matchDTO);
    public Match PartialUpdate(Integer matchId, MatchDTO matchDTO);
    public boolean Delete(Integer matchId);
    public boolean LogicalDelete(Integer matchId);

    String Create(MatchDTO matchDTO);
}
