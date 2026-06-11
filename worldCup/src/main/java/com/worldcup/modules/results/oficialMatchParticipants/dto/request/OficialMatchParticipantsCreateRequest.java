package com.worldcup.modules.results.oficialMatchParticipants.dto.request;

import com.worldcup.modules.results.oficialMatchParticipants.enums.MatchResultStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OficialMatchParticipantsCreateRequest {

    private Integer id_oficialMatch;

    private Integer id_team;

    private Integer goals;

    private MatchResultStatus matchResultStatus;

}
