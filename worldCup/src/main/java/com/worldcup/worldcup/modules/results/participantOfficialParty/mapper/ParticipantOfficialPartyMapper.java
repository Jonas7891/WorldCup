package com.worldcup.worldCup.modules.results.participantOfficialParty.mapper;

import com.worldcup.worldCup.modules.mundial.team.entity.Team;
import com.worldcup.worldCup.modules.mundial.team.repository.TeamRepository;
import com.worldcup.worldCup.modules.results.oficialMatches.entity.OficialMatch;
import com.worldcup.worldCup.modules.results.oficialMatches.repository.OficialMatchRepository;
import com.worldcup.worldCup.modules.results.participantOfficialParty.dto.ParticipantOfficialPartyDTO;
import com.worldcup.worldCup.modules.results.participantOfficialParty.entity.ParticipantOfficialParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParticipantOfficialPartyMapper {

    @Autowired
    private OficialMatchRepository oficial_match_repository;

    @Autowired
    private TeamRepository team_repository;

    public ParticipantOfficialPartyDTO toDTO(ParticipantOfficialParty entity) {
        if (entity == null) {
            return null;
        }

        ParticipantOfficialPartyDTO dto = new ParticipantOfficialPartyDTO();
        dto.setId_participant_official_party(entity.getId_participant_official_party());
        dto.setId_official_match(entity.getOfficial_match() != null ? entity.getOfficial_match().getId_official_match() : null);
        dto.setId_team(entity.getTeam() != null ? entity.getTeam().getId_team() : null);
        dto.setGoals(entity.getGoals());
        dto.setResult(entity.getResult());

        return dto;
    }

    public ParticipantOfficialParty toEntity(ParticipantOfficialPartyDTO dto) {
        if (dto == null) {
            return null;
        }

        ParticipantOfficialParty entity = new ParticipantOfficialParty();
        entity.setId_participant_official_party(dto.getId_participant_official_party());
        entity.setGoals(dto.getGoals() != null ? dto.getGoals() : 0);
        entity.setResult(dto.getResult());

        return entity;
    }
}

