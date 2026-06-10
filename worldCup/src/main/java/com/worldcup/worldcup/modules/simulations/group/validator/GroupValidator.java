package com.worldcup.worldCup.modules.simulations.group.validator;

import com.worldcup.worldCup.modules.simulations.group.dto.GroupDTO;
import org.springframework.stereotype.Component;

@Component
public class GroupValidator {

    public boolean validateGroupDTO(GroupDTO groupDTO) {
        if (groupDTO == null) {
            return false;
        }
        if (groupDTO.getName() == null || groupDTO.getName().isEmpty() || groupDTO.getName().length() > 100) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío y/o superar un 1 carácter.");
        }
        if (groupDTO.getId_group() <= 0) {
            throw new IllegalArgumentException("El identificador del grupo debe ser mayor que cero.");
        }
        return true;
    }

    public void validateGroupId(Integer groupId) {
        if (groupId == null || groupId <= 0) {
            throw new IllegalArgumentException("El identificador del grupo debe ser mayor que cero.");
        }
    }
}
