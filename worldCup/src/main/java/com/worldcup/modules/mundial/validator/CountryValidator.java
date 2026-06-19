package com.worldcup.modules.mundial.validator;

import com.worldcup.modules.mundial.dto.CountryDTO;
import org.springframework.stereotype.Component;

@Component
public class CountryValidator {

    public boolean validateCountryDTO(CountryDTO countryDTO) {
        if (countryDTO == null) {
            return false;
        }
        if (countryDTO.getName_country() == null || countryDTO.getName_country().isEmpty() || countryDTO.getName_country().length() > 100) {
            throw new IllegalArgumentException("Country name cannot be empty and/or exceed 100 characters.");
        }
        if (countryDTO.getFifa_code() == null || countryDTO.getFifa_code().isEmpty() || countryDTO.getFifa_code().length() != 3) {
            throw new IllegalArgumentException("FIFA code must be exactly 3 characters long.");
        }
        if (countryDTO.getContinent() == null || countryDTO.getContinent().isEmpty()) {
            throw new IllegalArgumentException("Country continent cannot be empty or null.");
        }
        return true;
    }

    public void validateCountryId(Integer countryId) {
        if (countryId == null || countryId <= 0) {
            throw new IllegalArgumentException("Country ID must be greater than zero.");
        }
    }
}
