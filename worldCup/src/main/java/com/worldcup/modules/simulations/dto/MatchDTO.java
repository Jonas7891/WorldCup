package com.worldcup.modules.simulations.dto;

import com.worldcup.modules.simulations.entity.EnumMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {
    private Integer id_match;
    private Integer id_simulation;
    private Integer id_phase;
    private Integer id_stadium;
    private LocalDateTime matchDate;
    private EnumMatch status;
}