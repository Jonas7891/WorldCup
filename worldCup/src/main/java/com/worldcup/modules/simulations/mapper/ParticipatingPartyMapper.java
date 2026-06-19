package com.worldcup.modules.simulations.mapper;

import com.worldcup.modules.mundial.entity.Team;
import com.worldcup.modules.simulations.dto.ParticipatingPartyDTO;
import com.worldcup.modules.simulations.entity.ParticipatingParty;
import com.worldcup.modules.simulations.entity.Match;
import org.springframework.stereotype.Component;

@Component
public class ParticipatingPartyMapper {

    public ParticipatingPartyDTO toDTO(ParticipatingParty entity) {
        if (entity == null) return null;

        ParticipatingPartyDTO dto = new ParticipatingPartyDTO();
        dto.setId_participating_party(entity.getId_participating_party());
        dto.setId_match(entity.getMatch() != null ? entity.getMatch().getId_match() : null);
        dto.setId_team(entity.getTeam() != null ? entity.getTeam().getIdTeam() : null);
        dto.setGoals(entity.getGoals());
        dto.setResult(entity.getResult());

        return dto;
    }

    public ParticipatingParty toEntity(ParticipatingPartyDTO dto) {
        if (dto == null) return null;

        ParticipatingParty entity = new ParticipatingParty();
        entity.setId_participating_party(dto.getId_participating_party());
        entity.setGoals(dto.getGoals() != null ? dto.getGoals() : 0);
        entity.setResult(dto.getResult());

        if (dto.getId_team() != null) {
            Team team = new Team();
            team.setIdTeam(dto.getId_team());
            entity.setTeam(team);
        }

        if (dto.getId_match() != null) {
            Match match = new Match();
            match.setId_match(dto.getId_match());
            entity.setMatch(match);
        }

        return entity;
    }
}