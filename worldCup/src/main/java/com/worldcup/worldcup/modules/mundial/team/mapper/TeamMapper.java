package com.worldcup.worldCup.modules.mundial.team.mapper;

import com.worldcup.worldCup.modules.mundial.team.dto.TeamDTO;
import com.worldcup.worldCup.modules.mundial.team.entity.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    public TeamDTO toDTO(Team team) {
        if (team == null) {
            return null;
        }

        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId_team(team.getId_team());
        teamDTO.setName(team.getName());
        teamDTO.setId_country(team.getCountry() != null ? team.getCountry().getId_country() : null);
        teamDTO.setId_group(team.getGroup() != null ? team.getGroup().getId_group() : null);
        teamDTO.setStatus(team.getStatus());

        return teamDTO;
    }

    public Team toEntity(TeamDTO teamDTO) {
        if (teamDTO == null) {
            return null;
        }

        Team team = new Team();
        if (teamDTO.getId_team() != null) {
            team.setId_team(teamDTO.getId_team());
        } else {
            team.setId_team(null);
        }
        team.setName(teamDTO.getName());
        team.setStatus(teamDTO.getStatus());

        return team;
    }
}
