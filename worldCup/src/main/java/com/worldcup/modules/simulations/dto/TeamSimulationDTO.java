package com.worldcup.modules.simulations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TeamSimulationDTO {
    private Integer id_teamSimulation;
    private Integer id_simulation;
    private Integer id_team;
    private Boolean status;
}
