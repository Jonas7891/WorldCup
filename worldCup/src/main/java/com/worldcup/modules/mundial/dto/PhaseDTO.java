package com.worldcup.modules.mundial.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PhaseDTO {
    private Integer id_phase;
    private String name;
    private Integer phase_order;
    private Boolean status;
}
