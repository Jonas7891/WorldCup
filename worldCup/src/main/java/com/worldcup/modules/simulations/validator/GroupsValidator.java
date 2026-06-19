package com.worldcup.modules.simulations.validator;

import com.worldcup.modules.simulations.dto.GroupsDTO;
import org.springframework.stereotype.Component;

@Component
public class GroupsValidator {

    public boolean validateGroupsDTO(GroupsDTO groupsDTO) {
        if (groupsDTO == null) {
            return false;
        }
        if (groupsDTO.getName() == null || groupsDTO.getName().isEmpty() || groupsDTO.getName().length() > 100) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío y/o superar un 1 carácter.");
        }
        if (groupsDTO.getId_group() <= 0) {
            throw new IllegalArgumentException("El identificador del grupo debe ser mayor que cero.");
        }
        return true;
    }

    public void validateGroupsId(Integer groupId) {
        if (groupId == null || groupId <= 0) {
            throw new IllegalArgumentException("El identificador del grupo debe ser mayor que cero.");
        }
    }
}
