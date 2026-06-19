package com.worldcup.modules.mundial.service.interfaces;

import com.worldcup.modules.mundial.dto.StadiumDTO;
import com.worldcup.modules.mundial.entity.Stadium;

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
