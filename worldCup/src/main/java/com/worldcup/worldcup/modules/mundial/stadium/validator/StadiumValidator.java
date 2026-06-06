package com.worldcup.worldcup.modules.mundial.stadium.validator;

import com.worldcup.worldcup.modules.mundial.stadium.dto.StadiumDTO;
import org.springframework.stereotype.Component;

@Component
public class StadiumValidator {

    public boolean validateStadiumDTO(StadiumDTO stadiumDTO) {
        if (stadiumDTO == null) {
            return false;
        }
        if (stadiumDTO.getName() == null || stadiumDTO.getName().isEmpty() || stadiumDTO.getName().length() > 100) {
            throw new IllegalArgumentException("El nombre del estadio no puede estar vacío y/o superar los 100 caracteres.");
        }
        if (stadiumDTO.getCity() == null || stadiumDTO.getCity().isEmpty() || stadiumDTO.getCity().length() > 100) {
            throw new IllegalArgumentException("El nombre de la ciudad no puede estar vacío y/o superar los 100 caracteres.");
        }
        if (stadiumDTO.getCapacity() == null || stadiumDTO.getCapacity() <= 0) {
            throw new IllegalArgumentException("La capacidad del estadio debe ser mayor que cero.");
        }
        return true;
    }

    public void validateStadiumId(Integer stadiumId) {
        if (stadiumId == null || stadiumId <= 0) {
            throw new IllegalArgumentException("El identificador del estadio debe ser mayor que cero.");
        }
    }
}
