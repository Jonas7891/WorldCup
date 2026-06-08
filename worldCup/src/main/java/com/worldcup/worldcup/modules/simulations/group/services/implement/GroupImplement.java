package com.worldcup.worldcup.modules.simulations.group.services.implement;

import com.worldcup.worldcup.modules.simulations.group.dto.GroupDTO;
import com.worldcup.worldcup.modules.simulations.group.entity.Group;
import com.worldcup.worldcup.modules.simulations.group.mapper.GroupMapper;
import com.worldcup.worldcup.modules.simulations.group.repository.GroupRepository;
import com.worldcup.worldcup.modules.simulations.group.services.interfaces.IGroup;
import com.worldcup.worldcup.modules.simulations.group.validator.GroupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GroupImplement implements IGroup {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private GroupValidator groupValidator;

    @Override
    public String Create(GroupDTO groupDTO){
        try {
            groupValidator.validateGroupDTO(groupDTO);
            if (groupDTO.getStatus() == null) {
                groupDTO.setStatus(Boolean.TRUE);
            }

            Group group = groupMapper.toEntity(groupDTO);
            group.setId_group(null);
            if (groupRepository.existsByName(group.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El grupo con ese nombre ya existe: " + group.getName());
            }

            try {
                groupRepository.save(group);
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
    public String CreateCountry(GroupDTO groupDTO) {
        return Create(groupDTO);
    }

    @Override
    public List<Group> GetAll() {
        try {
                return groupRepository.findByStatusTrue();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los grupos: " + e.getMessage());
        }
    }

    @Override
    public Group GetById(Integer groupId) {
        try {
            groupValidator.validateGroupId(groupId);

            Optional<Group> group = groupRepository.findById(groupId);
            if (group.isEmpty()) {
                throw new RuntimeException("Grupo no encontrado con ID: " + groupId);
            }
            return group.get();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el grupo: " + e.getMessage());
        }
    }

    @Override
    public Group Update(Integer groupId, GroupDTO groupDTO) {
        try {
            groupValidator.validateGroupId(groupId);
            groupValidator.validateGroupDTO(groupDTO);

            Optional<Group> groupExistente = groupRepository.findById(groupId);
            if (groupExistente.isEmpty()) {
                throw new RuntimeException("Grupo no encontrado con ID: " + groupId);
            }

            Group group = groupExistente.get();
            if (!group.getName().equals(groupDTO.getName()) &&
                groupRepository.existsByName(groupDTO.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El grupo con ese nombre ya existe: " + groupDTO.getName());
            }

            group.setName(groupDTO.getName());
            group.setId_group(groupDTO.getId_group());
            if (groupDTO.getStatus() != null) {
                group.setStatus(groupDTO.getStatus());
            }

            return groupRepository.save(group);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el grupo: " + e.getMessage());
        }
    }

    @Override
    public Group PartialUpdate(Integer groupId, GroupDTO groupDTO) {
        try {
            groupValidator.validateGroupId(groupId);

            Optional<Group> groupExistente = groupRepository.findById(groupId);
            if (groupExistente.isEmpty()) {
                throw new RuntimeException("Grupo no encontrado con ID: " + groupId);
            }

            Group group = groupExistente.get();
            if (groupDTO.getName() != null) {
                if (!group.getName().equals(groupDTO.getName()) &&
                    groupRepository.existsByName(groupDTO.getName())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "El grupo con ese nombre ya existe: " + groupDTO.getName());
                }
                group.setName(groupDTO.getName());
            }
            if (groupDTO.getId_group() > 0) {
                group.setId_group(groupDTO.getId_group());
            }
            if (groupDTO.getStatus() != null) {
                group.setStatus(groupDTO.getStatus());
            }

            return groupRepository.save(group);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar parcialmente el grupo: " + e.getMessage());
        }
    }

    @Override
        public boolean Delete(Integer groupId) {
        try {
            groupValidator.validateGroupId(groupId);

            Optional<Group> group = groupRepository.findById(groupId);
            if (group.isEmpty()) {
                throw new RuntimeException("Grupo no encontrado con ID: " + groupId);
            }

            groupRepository.deleteById(groupId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el grupo: " + e.getMessage());
        }
    }

    @Override
    public boolean LogicalDelete(Integer groupId) {
        try {
            groupValidator.validateGroupId(groupId);

            Optional<Group> groupExistente = groupRepository.findById(groupId);
            if (groupExistente.isEmpty()) {
                throw new RuntimeException("Grupo no encontrado con ID: " + groupId);
            }

            Group group = groupExistente.get();
            group.setStatus(Boolean.FALSE);
            groupRepository.save(group);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar lógicamente el grupo: " + e.getMessage());
        }
    }
}