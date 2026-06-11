package com.worldcup.modules.simulations.participatingParty.dto;

import com.worldcup.modules.simulations.participatingParty.entity.EnumParticipatingPartyResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipatingPartyDTO {
    private Integer id_participating_party;
    private Integer id_match;
    private Integer id_team;
    private Integer goals;
    private EnumParticipatingPartyResult result;
}

