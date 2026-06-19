package com.worldcup.modules.mundial.service.interfaces;

import com.worldcup.modules.mundial.dto.CountryDTO;
import com.worldcup.modules.mundial.entity.Country;

import java.util.List;

public interface ICountry {
    public String CreateCountry(CountryDTO countryDTO);
    public List<Country> GetAll();
    public Country GetById(Integer countryId);
    public Country Update(Integer countryId, CountryDTO countryDTO);
    public Country PartialUpdate(Integer countryId, CountryDTO countryDTO);
    public boolean Delete(Integer countryId);
    public boolean LogicalDelete(Integer countryId);

    String Create(CountryDTO countryDTO);
}
