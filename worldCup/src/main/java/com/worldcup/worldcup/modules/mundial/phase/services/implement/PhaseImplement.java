package com.worldcup.worldCup.modules.mundial.phase.services.implement;

import com.worldcup.worldCup.modules.mundial.phase.dto.PhaseDTO;
import com.worldcup.worldCup.modules.mundial.phase.entity.Phase;
import com.worldcup.worldCup.modules.mundial.phase.mapper.PhaseMapper;
import com.worldcup.worldCup.modules.mundial.phase.repository.PhaseRepository;
import com.worldcup.worldCup.modules.mundial.phase.services.interfaces.IPhase;
import com.worldcup.worldCup.modules.mundial.phase.validator.PhaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PhaseImplement implements IPhase {
    @Autowired
    private PhaseRepository phaseRepository;

    @Autowired
    private PhaseMapper phaseMapper;

    @Autowired
    private PhaseValidator phaseValidator;

    @Override
    public String Create(PhaseDTO phaseDTO){
        try {
            phaseValidator.validatePhaseDTO(phaseDTO);
            // Ensure status defaults to true when creating
            if (phaseDTO.getStatus() == null) {
                phaseDTO.setStatus(Boolean.TRUE);
            }

            Phase phase = phaseMapper.toEntity(phaseDTO);
            // Ensure we don't accidentally update an existing row when creating: reset id to null
            phase.setId_phase(null);
            // Check for duplicates by name before insert to avoid unique constraint exceptions
            if (phaseRepository.existsByName(phase.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "La fase con ese nombre ya existe: " + phase.getName());
            }

            try {
                phaseRepository.save(phase);
            } catch (DataIntegrityViolationException ex) {
                // In case a race condition still occurs, map to a 409 Conflict with helpful message
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al crear la fase: posible duplicado de datos.", ex);
            }

            return "Fase creada exitosamente";
        } catch (IllegalArgumentException e) {
            return "Error de validación: " + e.getMessage();
        } catch (Exception e) {
            return "Error al crear la fase: " + e.getMessage();
        }
    }

    @Override
    public String CreateCountry(PhaseDTO phaseDTO) {
        return Create(phaseDTO);
    }

    @Override
    public List<Phase> GetAll() {
        try {
                // Return only active phases by default
                return phaseRepository.findByStatusTrue();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las fases: " + e.getMessage());
        }
    }

    @Override
    public Phase GetById(Integer phaseId) {
        try {
            phaseValidator.validatePhaseId(phaseId);

            Optional<Phase> phase = phaseRepository.findById(phaseId);
            if (phase.isEmpty()) {
                throw new RuntimeException("Fase no encontrada con ID: " + phaseId);
            }
            return phase.get();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la fase: " + e.getMessage());
        }
    }

    @Override
    public Phase Update(Integer phaseId, PhaseDTO phaseDTO) {
        try {
            phaseValidator.validatePhaseId(phaseId);
            phaseValidator.validatePhaseDTO(phaseDTO);

            Optional<Phase> phaseExistente = phaseRepository.findById(phaseId);
            if (phaseExistente.isEmpty()) {
                throw new RuntimeException("Fase no encontrada con ID: " + phaseId);
            }

            Phase phase = phaseExistente.get();
            // Check if new name already exists and is different from current
            if (!phase.getName().equals(phaseDTO.getName()) &&
                phaseRepository.existsByName(phaseDTO.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "La fase con ese nombre ya existe: " + phaseDTO.getName());
            }

            phase.setName(phaseDTO.getName());
            phase.setPhase_order(phaseDTO.getPhase_order());
            if (phaseDTO.getStatus() != null) {
                phase.setStatus(phaseDTO.getStatus());
            }

            return phaseRepository.save(phase);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la fase: " + e.getMessage());
        }
    }

    @Override
    public Phase PartialUpdate(Integer phaseId, PhaseDTO phaseDTO) {
        try {
            phaseValidator.validatePhaseId(phaseId);

            Optional<Phase> phaseExistente = phaseRepository.findById(phaseId);
            if (phaseExistente.isEmpty()) {
                throw new RuntimeException("Fase no encontrada con ID: " + phaseId);
            }

            Phase phase = phaseExistente.get();
            // Only update fields that are provided (non-null / non-zero)
            if (phaseDTO.getName() != null) {
                // Check if new name already exists
                if (!phase.getName().equals(phaseDTO.getName()) &&
                    phaseRepository.existsByName(phaseDTO.getName())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "La fase con ese nombre ya existe: " + phaseDTO.getName());
                }
                phase.setName(phaseDTO.getName());
            }
            if (phaseDTO.getPhase_order() != null && phaseDTO.getPhase_order() > 0) {
                phase.setPhase_order(phaseDTO.getPhase_order());
            }
            if (phaseDTO.getStatus() != null) {
                phase.setStatus(phaseDTO.getStatus());
            }

            return phaseRepository.save(phase);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar parcialmente la fase: " + e.getMessage());
        }
    }

    @Override
    public boolean Delete(Integer phaseId) {
        try {
            phaseValidator.validatePhaseId(phaseId);

            Optional<Phase> phase = phaseRepository.findById(phaseId);
            if (phase.isEmpty()) {
                throw new RuntimeException("Fase no encontrada con ID: " + phaseId);
            }

            phaseRepository.deleteById(phaseId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la fase: " + e.getMessage());
        }
    }

    @Override
    public boolean LogicalDelete(Integer phaseId) {
        try {
            phaseValidator.validatePhaseId(phaseId);

            Optional<Phase> phaseExistente = phaseRepository.findById(phaseId);
            if (phaseExistente.isEmpty()) {
                throw new RuntimeException("Fase no encontrada con ID: " + phaseId);
            }

            Phase phase = phaseExistente.get();
            // set status to false to mark as logically deleted
            phase.setStatus(Boolean.FALSE);
            phaseRepository.save(phase);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar lógicamente la fase: " + e.getMessage());
        }
    }
}