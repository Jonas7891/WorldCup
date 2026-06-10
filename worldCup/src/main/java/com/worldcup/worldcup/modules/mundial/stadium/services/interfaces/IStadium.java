package com.worldcup.worldCup.modules.mundial.stadium.services.interfaces;

import com.worldcup.worldCup.modules.mundial.stadium.dto.StadiumDTO;
import com.worldcup.worldCup.modules.mundial.stadium.entity.Stadium;
import java.util.List;

public interface IStadium {
    public String CreateCountry(StadiumDTO stadiumDTO);
    public List<Stadium> GetAll();
    public Stadium GetById(Integer stadiumId);
    public Stadium Update(Integer stadiumId, StadiumDTO stadiumDTO);
    public Stadium PartialUpdate(Integer stadiumId, StadiumDTO stadiumDTO);
    public boolean Delete(Integer stadiumId);
    public boolean LogicalDelete(Integer stadiumId);

    String Create(StadiumDTO stadiumDTO);
}
