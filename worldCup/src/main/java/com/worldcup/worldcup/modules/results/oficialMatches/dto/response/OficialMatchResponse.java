package com.worldcup.worldCup.modules.results.oficialMatches.dto.response;


import com.worldcup.worldCup.modules.results.oficialMatches.enums.OficialMatchStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OficialMatchResponse {

    private Long id_oficialMatch;

    private String phase;

    private String stadium;

    private LocalDateTime date;

    private OficialMatchStatus oficialMatchStatus;

}
