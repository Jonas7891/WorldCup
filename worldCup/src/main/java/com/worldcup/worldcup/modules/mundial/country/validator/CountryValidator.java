package com.worldcup.worldcup.modules.mundial.country.validator;

import com.worldcup.worldcup.modules.mundial.country.dto.CountryDTO;
import org.springframework.stereotype.Component;

@Component
public class CountryValidator {

    public boolean validateCountryDTO(CountryDTO countryDTO) {
        if (countryDTO == null) {
            return false;
        }
        if (countryDTO.getName_country() == null || countryDTO.getName_country().isEmpty() || countryDTO.getName_country().length() > 100) {
            throw new IllegalArgumentException("El nombre del país no puede estar vacío y/o superar los 100 caracteres.");
        }
        if (countryDTO.getRanking_fifa() == null || countryDTO.getRanking_fifa() <= 0) {
            throw new IllegalArgumentException("La clasificación del país en la FIFA debe ser mayor que cero.");
        }
        if (countryDTO.getContinent() == null || countryDTO.getContinent().isEmpty()) {
            throw new IllegalArgumentException("El continente del país no puede estar vacío o ser nulo.");
        }
        return true;
    }

    public void validateCountryId(Integer countryId) {
        if (countryId == null || countryId <= 0) {
            throw new IllegalArgumentException("El identificador del país debe ser mayor que cero.");
        }
    }
}
