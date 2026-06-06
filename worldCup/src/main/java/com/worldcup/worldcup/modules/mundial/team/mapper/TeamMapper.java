package com.worldcup.worldcup.modules.mundial.team.mapper;

import com.worldcup.worldcup.modules.mundial.team.dto.TeamDTO;
import com.worldcup.worldcup.modules.mundial.team.entity.Team;
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
        teamDTO.setId_country(team.getId_country());
        teamDTO.setId_group(team.getId_group());
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
        team.setId_country(teamDTO.getId_country());
        team.setId_group(teamDTO.getId_group());
        team.setStatus(teamDTO.getStatus());

        return team;
    }
}
