package com.worldcup.modules.mundial.service.implement;

import java.util.List;
import java.util.Optional;

import com.worldcup.modules.mundial.validator.CountryValidator;
import com.worldcup.modules.mundial.dto.CountryDTO;
import com.worldcup.modules.mundial.entity.Country;
import com.worldcup.modules.mundial.mapper.CountryMapper;
import com.worldcup.modules.mundial.repository.CountryRepository;
import com.worldcup.modules.mundial.service.interfaces.ICountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

@Service
public class CountryImplement implements ICountry {
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CountryValidator countryValidator;

    @Override
    public String Create(CountryDTO countryDTO){
        try {
            countryValidator.validateCountryDTO(countryDTO);
            // Ensure status defaults to true when creating
            if (countryDTO.getStatus() == null) {
                countryDTO.setStatus(Boolean.TRUE);
            }

            Country country = countryMapper.toEntity(countryDTO);
            country.setId_country(null);

            try {
                countryRepository.save(country);
            } catch (DataIntegrityViolationException ex) {
                // In case a race condition still occurs, map to a 409 Conflict with helpful message
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al crear el país: posible duplicado de datos.", ex);
            }

            return "País creado exitosamente";
        } catch (IllegalArgumentException e) {
            return "Error de validación: " + e.getMessage();
        } catch (Exception e) {
            return "Error al crear el país: " + e.getMessage();
        }
    }

    @Override
    public String CreateCountry(CountryDTO countryDTO) {
        return Create(countryDTO);
    }

    @Override
    public List<Country> GetAll() {
        try {
                // Return only active countries by default
                return countryRepository.findByStatusTrue();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los países: " + e.getMessage());
        }
    }

    @Override
    public Country GetById(Integer countryId) {
        try {
            countryValidator.validateCountryId(countryId);

            Optional<Country> country = countryRepository.findById(countryId);
            if (country.isEmpty()) {
                throw new RuntimeException("País no encontrado con ID: " + countryId);
            }
            return country.get();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el país: " + e.getMessage());
        }
    }

    @Override
    public Country Update(Integer countryId, CountryDTO countryDTO) throws ResponseStatusException {
        try {
            countryValidator.validateCountryId(countryId);
            countryValidator.validateCountryDTO(countryDTO);

            Optional<Country> countryExistente = countryRepository.findById(countryId);
            if (countryExistente.isEmpty()) {
                throw new RuntimeException("País no encontrado con ID: " + countryId);
            }

            Country country = countryExistente.get();
            // Check if new name already exists and is different from current
            if (!country.getName_country().equals(countryDTO.getName_country()) &&
                countryRepository.existsByName_country(countryDTO.getName_country())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El país con ese nombre ya existe: " + countryDTO.getName_country());
            }

            country.setName_country(countryDTO.getName_country());
            country.setRanking_fifa(countryDTO.getRanking_fifa());
            country.setContinent(countryDTO.getContinent());
            if (countryDTO.getStatus() != null) {
                country.setStatus(countryDTO.getStatus());
            }

            return countryRepository.save(country);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el país: " + e.getMessage());
        }
    }

    @Override
    public Country PartialUpdate(Integer countryId, CountryDTO countryDTO) {
        try {
            countryValidator.validateCountryId(countryId);

            Optional<Country> countryExistente = countryRepository.findById(countryId);
            if (countryExistente.isEmpty()) {
                throw new RuntimeException("País no encontrado con ID: " + countryId);
            }

            Country country = countryExistente.get();
            // Only update fields that are provided (non-null / non-zero)
            if (countryDTO.getName_country() != null) {
                // Check if new name already exists
                if (!country.getName_country().equals(countryDTO.getName_country()) &&
                    countryRepository.existsByName_country(countryDTO.getName_country())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "El país con ese nombre ya existe: " + countryDTO.getName_country());
                }
                country.setName_country(countryDTO.getName_country());
            }
            if (countryDTO.getRanking_fifa() != null && countryDTO.getRanking_fifa() > 0) {
                country.setRanking_fifa(countryDTO.getRanking_fifa());
            }
            if (countryDTO.getContinent() != null) {
                country.setContinent(countryDTO.getContinent());
            }
            if (countryDTO.getStatus() != null) {
                country.setStatus(countryDTO.getStatus());
            }

            return countryRepository.save(country);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar parcialmente el país: " + e.getMessage());
        }
    }

    @Override
        public boolean Delete(Integer countryId) {
        try {
            countryValidator.validateCountryId(countryId);

            Optional<Country> country = countryRepository.findById(countryId);
            if (country.isEmpty()) {
                throw new RuntimeException("País no encontrado con ID: " + countryId);
            }

            countryRepository.deleteById(countryId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el país: " + e.getMessage());
        }
    }

    @Override
    public boolean LogicalDelete(Integer countryId) {
        try {
            countryValidator.validateCountryId(countryId);

            Optional<Country> countryExistente = countryRepository.findById(countryId);
            if (countryExistente.isEmpty()) {
                throw new RuntimeException("País no encontrado con ID: " + countryId);
            }

            Country country = countryExistente.get();

            // set status to false to mark as logically deleted
            country.setStatus(Boolean.FALSE);
            countryRepository.save(country);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar lógicamente el país: " + e.getMessage());
        }
    }
}