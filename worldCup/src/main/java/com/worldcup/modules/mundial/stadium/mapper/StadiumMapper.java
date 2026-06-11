package com.worldcup.modules.mundial.stadium.mapper;

import com.worldcup.modules.mundial.stadium.dto.StadiumDTO;
import com.worldcup.modules.mundial.stadium.entity.Stadium;
import org.springframework.stereotype.Component;

@Component
public class StadiumMapper {
    public StadiumDTO toDTO(Stadium stadium) {
        if (stadium == null) {
            return null;
        }

        StadiumDTO stadiumDTO = new StadiumDTO();
        stadiumDTO.setId_stadium(stadium.getId_stadium());
        stadiumDTO.setName(stadium.getName());
        stadiumDTO.setCity(stadium.getCity());
        stadiumDTO.setCapacity(stadium.getCapacity());
        stadiumDTO.setStatus(stadium.getStatus());

        return stadiumDTO;
    }

    public Stadium toEntity(StadiumDTO stadiumDTO) {
        if (stadiumDTO == null) {
            return null;
        }

        Stadium stadium = new Stadium();
        // Only set id if provided; for creations id may be null
        if (stadiumDTO.getId_stadium() != null) {
            stadium.setId_stadium(stadiumDTO.getId_stadium());
        } else {
            stadium.setId_stadium(null);
        }
        stadium.setName(stadiumDTO.getName());
        stadium.setCity(stadiumDTO.getCity());
        stadium.setCapacity(stadiumDTO.getCapacity());
        stadium.setStatus(stadiumDTO.getStatus());

        return stadium;
    }
}
