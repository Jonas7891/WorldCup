package com.worldcup.worldcup.modules.simulations.simulation.dto;

import com.worldcup.worldcup.modules.simulations.simulation.entity.EnumSimulation;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SimulationDTO {
    private Integer id_simulation;
    private String name;
    private LocalDateTime creation_date;
    private Integer id_user;
    private EnumSimulation status;
}
