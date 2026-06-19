package com.worldcup.modules.mundial.mapper;

import com.worldcup.modules.mundial.dto.CountryDTO;
import com.worldcup.modules.mundial.entity.Country;
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
        countryDTO.setFifa_code(country.getFifa_code());
        countryDTO.setContinent(country.getContinent());
        countryDTO.setRanking_fifa(country.getRanking_fifa());
        countryDTO.setStatus(country.getStatus());

        return countryDTO;
    }

    public Country toEntity(CountryDTO countryDTO) {
        if (countryDTO == null) {
            return null;
        }

        Country country = new Country();
        if (countryDTO.getId_country() != null) {
            country.setId_country(countryDTO.getId_country());
        } else {
            country.setId_country(null);
        }
        country.setName_country(countryDTO.getName_country());
        country.setFifa_code(countryDTO.getFifa_code());
        country.setContinent(countryDTO.getContinent());
        country.setRanking_fifa(countryDTO.getRanking_fifa());
        country.setStatus(countryDTO.getStatus());

        return country;
    }
}
