package com.worldcup.modules.results.oficialMatchParticipants.dto.response;

import com.worldcup.modules.results.oficialMatchParticipants.entity.OficialMatchParticipants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OficialMatchParticipantsResponse {

    private Integer id_oficialMatchParticipants;

    private Integer id_oficialMatch;

    private String team;

    private Integer goals;

    private OficialMatchParticipants oficialMatchParticipants;


}
