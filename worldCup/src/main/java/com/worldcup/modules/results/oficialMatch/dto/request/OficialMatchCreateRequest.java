package com.worldcup.modules.results.oficialMatch.dto.request;

import com.worldcup.modules.results.oficialMatch.enums.OficialMatchStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class OficialMatchCreateRequest {

    private Integer id_phase;

    private Integer id_stadium;

    private LocalDateTime date;

    private OficialMatchStatus oficialMatchStatus;

}
