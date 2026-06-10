package com.worldcup.worldcup.modules.simulations.teamSimulation.entity;

import com.worldcup.worldcup.modules.mundial.team.entity.Team;
import com.worldcup.worldcup.modules.simulations.simulation.entity.Simulation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="team_simulation")
@Table(name="team_simulation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamSimulation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_teamSimulation", nullable = false)
    private Integer id_teamSimulation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_simulation", nullable = false)
    private Simulation simulation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_team", nullable = false)
    private Team team;

    @Column(name="status", nullable = false)
    private Boolean status;
}
