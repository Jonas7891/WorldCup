package com.worldcup.worldCup.modules.mundial.phase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="phase")
@Table(name="phase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Phase {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_phase", nullable = false)
    private Integer id_phase;

    @Column(name="name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name="phase_order", nullable = false)
    private Integer phase_order;

    @Column(name="status", nullable = false)
    private Boolean status;
}
