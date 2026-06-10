package com.worldcup.worldCup.modules.simulations.group.entity;

import com.worldcup.worldCup.modules.simulations.simulation.entity.Simulation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="group")
@Table(name="group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Group {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_group", nullable = false)
    private Integer id_group;

    @Column(name="name", length = 1, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_simulation", nullable = false)
    private Simulation simulation;

    @Column(name="status", nullable = false)
    private Boolean status;
}
