package com.worldcup.worldcup.modules.results.participantOfficialParty.dto;

import com.worldcup.worldcup.modules.results.participantOfficialParty.entity.EnumOfficialPartyResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantOfficialPartyDTO {
    private Integer id_participant_official_party;
    private Integer id_official_match;
    private Integer id_team;
    private Integer goals;
    private EnumOfficialPartyResult result;
}

