package com.worldcup.modules.simulations.participatingParty.mapper;

import com.worldcup.modules.simulations.participatingParty.dto.ParticipatingPartyDTO;
import com.worldcup.modules.simulations.participatingParty.entity.ParticipatingParty;
import org.springframework.stereotype.Component;

@Component
public class ParticipatingPartyMapper {


    public ParticipatingPartyDTO toDTO(ParticipatingParty participatingParty) {
        if (participatingParty == null) {
            return null;
        }

        ParticipatingPartyDTO dto = new ParticipatingPartyDTO();
        dto.setId_participating_party(participatingParty.getId_participating_party());
        dto.setId_match(participatingParty.getMatch() != null ? participatingParty.getMatch().getId_match() : null);
        dto.setId_team(participatingParty.getTeam() != null ? participatingParty.getTeam().getId_team() : null);
        dto.setGoals(participatingParty.getGoals());
        dto.setResult(participatingParty.getResult());

        return dto;
    }

    public ParticipatingParty toEntity(ParticipatingPartyDTO dto) {
        if (dto == null) {
            return null;
        }

        ParticipatingParty participatingParty = new ParticipatingParty();
        participatingParty.setId_participating_party(dto.getId_participating_party());
        participatingParty.setGoals(dto.getGoals() != null ? dto.getGoals() : 0);
        participatingParty.setResult(dto.getResult());

        return participatingParty;
    }
}

