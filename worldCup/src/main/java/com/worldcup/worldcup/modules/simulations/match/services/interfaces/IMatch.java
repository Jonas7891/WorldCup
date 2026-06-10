package com.worldcup.worldCup.modules.simulations.match.services.interfaces;

import com.worldcup.worldCup.modules.simulations.match.dto.MatchDTO;
import com.worldcup.worldCup.modules.simulations.match.entity.Match;

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
