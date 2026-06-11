package com.worldcup.modules.mundial.stadium.services.implement;

import com.worldcup.modules.mundial.stadium.dto.StadiumDTO;
import com.worldcup.modules.mundial.stadium.entity.Stadium;
import com.worldcup.modules.mundial.stadium.mapper.StadiumMapper;
import com.worldcup.modules.mundial.stadium.repository.StadiumRepository;
import com.worldcup.modules.mundial.stadium.services.interfaces.IStadium;
import com.worldcup.modules.mundial.stadium.validator.StadiumValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StadiumImplement implements IStadium {
    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    private StadiumMapper stadiumMapper;

    @Autowired
    private StadiumValidator stadiumValidator;

    @Override
    public String Create(StadiumDTO stadiumDTO){
        try {
            stadiumValidator.validateStadiumDTO(stadiumDTO);
            // Ensure status defaults to true when creating
            if (stadiumDTO.getStatus() == null) {
                stadiumDTO.setStatus(Boolean.TRUE);
            }

            Stadium stadium = stadiumMapper.toEntity(stadiumDTO);
            // Ensure we don't accidentally update an existing row when creating: reset id to null
            stadium.setId_stadium(null);
            // Check for duplicates by name before insert to avoid unique constraint exceptions
            if (stadiumRepository.existsByName(stadium.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El estadio con ese nombre ya existe: " + stadium.getName());
            }

            try {
                stadiumRepository.save(stadium);
            } catch (DataIntegrityViolationException ex) {
                // In case a race condition still occurs, map to a 409 Conflict with helpful message
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al crear el estadio: posible duplicado de datos.", ex);
            }

            return "Estadio creado exitosamente";
        } catch (IllegalArgumentException e) {
            return "Error de validación: " + e.getMessage();
        } catch (Exception e) {
            return "Error al crear el estadio: " + e.getMessage();
        }
    }

    @Override
    public String CreateCountry(StadiumDTO stadiumDTO) {
        return Create(stadiumDTO);
    }

    @Override
    public List<Stadium> GetAll() {
        try {
                // Return only active stadiums by default
                return stadiumRepository.findByStatusTrue();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los estadios: " + e.getMessage());
        }
    }

    @Override
    public Stadium GetById(Integer stadiumId) {
        try {
            stadiumValidator.validateStadiumId(stadiumId);

            Optional<Stadium> stadium = stadiumRepository.findById(stadiumId);
            if (stadium.isEmpty()) {
                throw new RuntimeException("Estadio no encontrado con ID: " + stadiumId);
            }
            return stadium.get();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el estadio: " + e.getMessage());
        }
    }

    @Override
    public Stadium Update(Integer stadiumId, StadiumDTO stadiumDTO) {
        try {
            stadiumValidator.validateStadiumId(stadiumId);
            stadiumValidator.validateStadiumDTO(stadiumDTO);

            Optional<Stadium> stadiumExistente = stadiumRepository.findById(stadiumId);
            if (stadiumExistente.isEmpty()) {
                throw new RuntimeException("Estadio no encontrado con ID: " + stadiumId);
            }

            Stadium stadium = stadiumExistente.get();
            // Check if new name already exists and is different from current
            if (!stadium.getName().equals(stadiumDTO.getName()) &&
                stadiumRepository.existsByName(stadiumDTO.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El estadio con ese nombre ya existe: " + stadiumDTO.getName());
            }

            stadium.setName(stadiumDTO.getName());
            stadium.setCity(stadiumDTO.getCity());
            stadium.setCapacity(stadiumDTO.getCapacity());
            if (stadiumDTO.getStatus() != null) {
                stadium.setStatus(stadiumDTO.getStatus());
            }

            return stadiumRepository.save(stadium);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el estadio: " + e.getMessage());
        }
    }

    @Override
    public Stadium PartialUpdate(Integer stadiumId, StadiumDTO stadiumDTO) {
        try {
            stadiumValidator.validateStadiumId(stadiumId);

            Optional<Stadium> stadiumExistente = stadiumRepository.findById(stadiumId);
            if (stadiumExistente.isEmpty()) {
                throw new RuntimeException("Estadio no encontrado con ID: " + stadiumId);
            }

            Stadium stadium = stadiumExistente.get();
            // Only update fields that are provided (non-null / non-zero)
            if (stadiumDTO.getName() != null) {
                // Check if new name already exists
                if (!stadium.getName().equals(stadiumDTO.getName()) &&
                    stadiumRepository.existsByName(stadiumDTO.getName())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "El estadio con ese nombre ya existe: " + stadiumDTO.getName());
                }
                stadium.setName(stadiumDTO.getName());
            }
            if (stadiumDTO.getCity() != null) {
                stadium.setCity(stadiumDTO.getCity());
            }
            if (stadiumDTO.getCapacity() != null && stadiumDTO.getCapacity() > 0) {
                stadium.setCapacity(stadiumDTO.getCapacity());
            }
            if (stadiumDTO.getStatus() != null) {
                stadium.setStatus(stadiumDTO.getStatus());
            }

            return stadiumRepository.save(stadium);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar parcialmente el estadio: " + e.getMessage());
        }
    }

    @Override
    public boolean Delete(Integer stadiumId) {
        try {
            stadiumValidator.validateStadiumId(stadiumId);

            Optional<Stadium> stadium = stadiumRepository.findById(stadiumId);
            if (stadium.isEmpty()) {
                throw new RuntimeException("Estadio no encontrado con ID: " + stadiumId);
            }

            stadiumRepository.deleteById(stadiumId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el estadio: " + e.getMessage());
        }
    }

    @Override
    public boolean LogicalDelete(Integer stadiumId) {
        try {
            stadiumValidator.validateStadiumId(stadiumId);

            Optional<Stadium> stadiumExistente = stadiumRepository.findById(stadiumId);
            if (stadiumExistente.isEmpty()) {
                throw new RuntimeException("Estadio no encontrado con ID: " + stadiumId);
            }

            Stadium stadium = stadiumExistente.get();
            // set status to false to mark as logically deleted
            stadium.setStatus(Boolean.FALSE);
            stadiumRepository.save(stadium);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar lógicamente el estadio: " + e.getMessage());
        }
    }
}