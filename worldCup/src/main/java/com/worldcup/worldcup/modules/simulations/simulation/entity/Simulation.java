package com.worldcup.worldcup.modules.simulations.simulation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name="simulation")
@Table(name="simulation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Simulation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_simulation", nullable = false)
    private Integer id_simulation;

    @Column(name="name", length = 100, nullable = false)
    private String name;

    @Column(name="creation_date", nullable = false)
    private LocalDateTime creation_date;

    @Column(name="id_user", nullable = false)
    private Integer id_user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EnumSimulation status;
}
