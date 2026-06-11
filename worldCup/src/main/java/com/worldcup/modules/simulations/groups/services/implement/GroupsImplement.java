package com.worldcup.modules.simulations.groups.services.implement;

import com.worldcup.modules.simulations.groups.dto.GroupsDTO;
import com.worldcup.modules.simulations.groups.entity.Groups;
import com.worldcup.modules.simulations.groups.mapper.GroupsMapper;
import com.worldcup.modules.simulations.groups.repository.GroupsRepository;
import com.worldcup.modules.simulations.groups.services.interfaces.IGroups;
import com.worldcup.modules.simulations.groups.validator.GroupsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GroupsImplement implements IGroups {
    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private GroupsMapper groupsMapper;

    @Autowired
    private GroupsValidator groupsValidator;

    @Override
    public String Create(GroupsDTO groupsDTO){
        try {
            groupsValidator.validateGroupsDTO(groupsDTO);
            if (groupsDTO.getStatus() == null) {
                groupsDTO.setStatus(Boolean.TRUE);
            }

            Groups groups = groupsMapper.toEntity(groupsDTO);
            groups.setId_group(null);

            try {
                groupsRepository.save(groups);
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al crear el Grupo: posible duplicado de datos.", ex);
            }

            return "Grupo creado exitosamente";
        } catch (IllegalArgumentException e) {
            return "Error de validación: " + e.getMessage();
        } catch (Exception e) {
            return "Error al crear el Grupo: " + e.getMessage();
        }
    }

    @Override
    public String CreateCountry(GroupsDTO groupsDTO) {
        return Create(groupsDTO);
    }

    @Override
    public List<Groups> GetAll() {
        try {
                return groupsRepository.findByStatus(true);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los grupos: " + e.getMessage());
        }
    }

    @Override
    public Groups GetById(Integer groupId) {
        try {
            groupsValidator.validateGroupsId(groupId);

            Optional<Groups> group = groupsRepository.findById(groupId);
            if (group.isEmpty()) {
                throw new RuntimeException("Grupo no encontrado con ID: " + groupId);
            }
            return group.get();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el grupo: " + e.getMessage());
        }
    }

    @Override
    public Groups Update(Integer groupId, GroupsDTO groupsDTO) {
        try {
            groupsValidator.validateGroupsId(groupId);
            groupsValidator.validateGroupsDTO(groupsDTO);

            Optional<Groups> groupExistente = groupsRepository.findById(groupId);
            if (groupExistente.isEmpty()) {
                throw new RuntimeException("Grupo no encontrado con ID: " + groupId);
            }

             Groups groups = groupExistente.get();
             if (!groups.getName().equals(groupsDTO.getName()) &&
                 groupsRepository.existsByName(groupsDTO.getName())) {
                 throw new ResponseStatusException(HttpStatus.CONFLICT, "El grupo con ese nombre ya existe: " + groupsDTO.getName());
             }

             groups.setName(groupsDTO.getName());
             // Note: id_simulation is handled via Simulation entity relationship
             if (groupsDTO.getStatus() != null) {
                 groups.setStatus(groupsDTO.getStatus());
             }

            return groupsRepository.save(groups);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el grupo: " + e.getMessage());
        }
    }

    @Override
    public Groups PartialUpdate(Integer groupId, GroupsDTO groupsDTO) {
        try {
            groupsValidator.validateGroupsId(groupId);

            Optional<Groups> groupExistente = groupsRepository.findById(groupId);
            if (groupExistente.isEmpty()) {
                throw new RuntimeException("Grupo no encontrado con ID: " + groupId);
            }

             Groups groups = groupExistente.get();
             if (groupsDTO.getName() != null) {
                 if (!groups.getName().equals(groupsDTO.getName()) &&
                     groupsRepository.existsByName(groupsDTO.getName())) {
                     throw new ResponseStatusException(HttpStatus.CONFLICT, "El grupo con ese nombre ya existe: " + groupsDTO.getName());
                 }
                 groups.setName(groupsDTO.getName());
             }
             // Note: id_simulation is handled via Simulation entity relationship
             if (groupsDTO.getStatus() != null) {
                 groups.setStatus(groupsDTO.getStatus());
             }

            return groupsRepository.save(groups);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar parcialmente el grupo: " + e.getMessage());
        }
    }

    @Override
        public boolean Delete(Integer groupId) {
        try {
            groupsValidator.validateGroupsId(groupId);

            Optional<Groups> group = groupsRepository.findById(groupId);
            if (group.isEmpty()) {
                throw new RuntimeException("Grupo no encontrado con ID: " + groupId);
            }

            groupsRepository.deleteById(groupId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el grupo: " + e.getMessage());
        }
    }

    @Override
    public boolean LogicalDelete(Integer groupId) {
        try {
            groupsValidator.validateGroupsId(groupId);

            Optional<Groups> groupExistente = groupsRepository.findById(groupId);
            if (groupExistente.isEmpty()) {
                throw new RuntimeException("Grupo no encontrado con ID: " + groupId);
            }

            Groups groups = groupExistente.get();
            groups.setStatus(Boolean.FALSE);
            groupsRepository.save(groups);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar lógicamente el grupo: " + e.getMessage());
        }
    }
}