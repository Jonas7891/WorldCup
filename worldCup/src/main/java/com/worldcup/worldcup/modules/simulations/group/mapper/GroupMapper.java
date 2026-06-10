package com.worldcup.worldcup.modules.simulations.group.mapper;

import com.worldcup.worldcup.modules.simulations.group.dto.GroupDTO;
import com.worldcup.worldcup.modules.simulations.group.entity.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {
    public GroupDTO toDTO(Group group) {
        if (group == null) {
            return null;
        }

        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId_group(group.getId_group());
        groupDTO.setName(group.getName());
        groupDTO.setId_simulation(group.getSimulation() != null ? group.getSimulation().getId_simulation() : null);
        groupDTO.setStatus(group.getStatus());

        return groupDTO;
    }

    public Group toEntity(GroupDTO groupDTO) {
        if (groupDTO == null) {
            return null;
        }

        Group group = new Group();
        if (groupDTO.getId_group() != null) {
            group.setId_group(groupDTO.getId_group());
        } else {
            group.setId_group(null);
        }
        group.setName(groupDTO.getName());
        // Note: id_simulation is handled via Simulation entity relationship
        group.setStatus(groupDTO.getStatus());

        return group;
    }
}
