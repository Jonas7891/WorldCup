package com.worldcup.worldCup.modules.results.participantOfficialParty.validator;

import com.worldcup.worldCup.modules.results.participantOfficialParty.dto.ParticipantOfficialPartyDTO;
import org.springframework.stereotype.Component;

@Component
public class ParticipantOfficialPartyValidator {

    public boolean validateParticipantOfficialPartyDTO(ParticipantOfficialPartyDTO dto) {
        if (dto == null) {
            return false;
        }

        if (dto.getId_official_match() == null || dto.getId_official_match() <= 0) {
            throw new IllegalArgumentException("Official match ID must be greater than zero.");
        }

        if (dto.getId_team() == null || dto.getId_team() <= 0) {
            throw new IllegalArgumentException("Team ID must be greater than zero.");
        }

        if (dto.getGoals() == null || dto.getGoals() < 0) {
            throw new IllegalArgumentException("Goals cannot be negative.");
        }

        return true;
    }

    public void validateParticipantOfficialPartyId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Participant official party ID must be greater than zero.");
        }
    }
}

