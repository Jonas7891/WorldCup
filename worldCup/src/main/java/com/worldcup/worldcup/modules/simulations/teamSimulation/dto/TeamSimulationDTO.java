package com.worldcup.worldcup.modules.simulations.teamSimulation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
