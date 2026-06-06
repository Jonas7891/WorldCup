package com.worldcup.worldcup.modules.mundial.country.mapper;

import com.worldcup.worldcup.modules.mundial.country.dto.CountryDTO;
import com.worldcup.worldcup.modules.mundial.country.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {
    public CountryDTO toDTO(Country country) {
        if (country == null) {
            return null;
        }
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId_country(country.getId_country());
        countryDTO.setName_country(country.getName_country());
        countryDTO.setRanking_fifa(country.getRanking_fifa());
        countryDTO.setContinent(country.getContinent());
        countryDTO.setStatus(country.getStatus());

        return countryDTO;
    }

    public Country toEntity(CountryDTO countryDTO) {
        if (countryDTO == null) {
            return null;
        }

        Country country = new Country();
        // Only set id if provided; for creations id may be null
        if (countryDTO.getId_country() != null) {
            country.setId_country(countryDTO.getId_country());
        } else {
            country.setId_country(null);
        }
        country.setName_country(countryDTO.getName_country());
        country.setRanking_fifa(countryDTO.getRanking_fifa());
        country.setContinent(countryDTO.getContinent());
        country.setStatus(countryDTO.getStatus());

        return country;
    }
}
