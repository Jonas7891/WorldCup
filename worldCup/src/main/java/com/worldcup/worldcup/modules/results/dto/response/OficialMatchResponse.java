package com.worldcup.worldcup.modules.results.dto.response;


import com.worldcup.worldcup.modules.results.enums.OficialMatchStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class OficialMatchResponse {

    private Long id_oficialMatch;

    private String Phase;

    private String stadium;

    private LocalDateTime date;

    private OficialMatchStatus oficialMatchStatus;



}
