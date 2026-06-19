package com.worldcup.modules.simulations.mapper;

import com.worldcup.modules.simulations.dto.GroupsDTO;
import com.worldcup.modules.simulations.entity.Groups;
import org.springframework.stereotype.Component;

@Component
public class GroupsMapper {
    public GroupsDTO toDTO(Groups groups) {
        if (groups == null) {
            return null;
        }

        GroupsDTO groupsDTO = new GroupsDTO();
        groupsDTO.setId_group(groups.getId_group());
        groupsDTO.setName(groups.getName());
        groupsDTO.setId_simulation(groups.getSimulation() != null ? groups.getSimulation().getId_simulation() : null);
        groupsDTO.setStatus(groups.getStatus());

        return groupsDTO;
    }

    public Groups toEntity(GroupsDTO groupsDTO) {
        if (groupsDTO == null) {
            return null;
        }

        Groups groups = new Groups();
        if (groupsDTO.getId_group() != null) {
            groups.setId_group(groupsDTO.getId_group());
        } else {
            groups.setId_group(null);
        }
        groups.setName(groupsDTO.getName());
        // Note: id_simulation is handled via Simulation entity relationship
        groups.setStatus(groupsDTO.getStatus());

        return groups;
    }
}
