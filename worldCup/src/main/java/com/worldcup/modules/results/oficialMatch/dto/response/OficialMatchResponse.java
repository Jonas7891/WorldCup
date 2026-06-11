package com.worldcup.modules.results.oficialMatch.dto.response;

import com.worldcup.modules.results.oficialMatch.enums.OficialMatchStatus;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OficialMatchResponse {

    private Integer id_oficialMatch;

    private String phase;

    private String stadium;

    private LocalDateTime date;

    private OficialMatchStatus oficialMatchStatus;

}
